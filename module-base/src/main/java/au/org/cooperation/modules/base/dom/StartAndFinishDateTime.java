package au.org.cooperation.modules.base.dom;

import java.time.Duration;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import javax.jdo.annotations.*;

import org.apache.isis.applib.annotation.Action;
import org.apache.isis.applib.annotation.Editing;
import org.apache.isis.applib.annotation.Optionality;
import org.apache.isis.applib.annotation.Parameter;
import org.apache.isis.applib.annotation.ParameterLayout;
import org.apache.isis.applib.annotation.Property;
//import org.joda.time.LocalDateTime;
//import org.joda.time.Duration;

/**
 * An interval as represented by a start and finish date-times on the same date
 * 
 * Implements time accounting requirements
 * 
 */
@PersistenceCapable()
@Inheritance(strategy = InheritanceStrategy.SUBCLASS_TABLE)
public abstract class StartAndFinishDateTime {

	protected LocalDateTime start;
	protected LocalDateTime end;

	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public LocalDateTime getStart() {
		return start;
	}

	public void setStart(final LocalDateTime start) {
		this.start = start;
	}

	@Property(editing = Editing.DISABLED)
	@Column(allowsNull = "true")
	public LocalDateTime getEnd() {
		return end;
	}

	public void setEnd(final LocalDateTime end) {
		this.end = end;
	}

	@NotPersistent
	public String getIntervalLengthFormatted() {
		if (getStart() != null && getEnd() != null) {
			Duration duration = Duration.between(getStart(), getEnd());
			Long hours = duration.toHours();
			Long minutes = duration.toMinutes();
			if (hours > 0)
				minutes = minutes - hours * 60;
			return String.format("%01d:%02d", hours, minutes);
		} else
			return null;
	}

	@NotPersistent
	public Long getIntervalLengthInMinutes() {
		if (getStart() != null && getEnd() != null) {
			Duration duration = Duration.between(getStart(), getEnd());
			return duration.toMinutes();
		} else
			return null;
	}

	public static String validateStartAndFinish(LocalDateTime start, LocalDateTime finish) {
		if (start != null && finish != null) {
			if (finish.isBefore(start) || finish.equals(start))
				return "End is before or equal to Start";
			else {
				Duration duration = Duration.between(start, finish);
				if (duration.toMinutes() == 0)
					return "End is equal to Start";
				if (duration.toHours() > 12)
					return "End and Start are not in the same 12 hour period";
				if (finish.getDayOfWeek() != start.getDayOfWeek()) {
					return "End and Start are on different days of the week";
				}
			}
		}
		return null;
	}

	@Action()
	public StartAndFinishDateTime updateStart(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Start Time") LocalDateTime start) {
		setStart(trimSeconds(start));
		return this;
	}

	public LocalDateTime default0UpdateStart() {
		if (getStart() == null)
			return getEnd();
		else
			return getStart();
	}

	public String validateUpdateStart(LocalDateTime start) {
		return validateStartAndFinish(start, getEnd());
	}

	// NOTE Must keep end date time optional to be able to change start date
	// time to anything
	@Action()
	public StartAndFinishDateTime updateEnd(
			@Parameter(optionality = Optionality.OPTIONAL) @ParameterLayout(named = "End Time") LocalDateTime end) {
		setEnd(trimSeconds(end));
		return this;
	}

	public LocalDateTime default0UpdateEnd() {
		if (getEnd() == null)
			return getStart();
		else
			return getEnd();
	}

	public String validateUpdateEnd(LocalDateTime end) {
		return validateStartAndFinish(getStart(), end);
	}

	@Action()
	public StartAndFinishDateTime updateEndOffStart(
			@Parameter(optionality = Optionality.MANDATORY) @ParameterLayout(named = "Add N Minutes to Start LocalDateTime Time") Integer minutes) {
		setEnd(getStart().plusMinutes(minutes));
		return this;
	}

	public String validateUpdateEndOffStart(Integer minutes) {
		return validateStartAndFinish(getStart(), getStart().plusMinutes(minutes));
	}

	public String disableUpdateEndOffStart() {
		if (getStart() == null)
			return "Start LocalDateTime Time is Not Set";
		else
			return null;
	}

	protected LocalDateTime trimSeconds(LocalDateTime dateTime) {
		if (dateTime == null)
			return null;
		return dateTime.truncatedTo(ChronoUnit.MINUTES);
	}
}
