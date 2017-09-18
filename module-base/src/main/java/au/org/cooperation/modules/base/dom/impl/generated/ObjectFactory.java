//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.17 at 09:22:29 PM AEST 
//


package au.org.cooperation.modules.base.dom.impl.generated;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the domainapp.modules.base.dom.impl.generated package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _Organisation_QNAME = new QName("http://www.example.org/OneIdSchema", "organisation");
    private final static QName _Success_QNAME = new QName("http://www.example.org/OneIdSchema", "success");
    private final static QName _Algorithm_QNAME = new QName("http://www.example.org/OneIdSchema", "algorithm");
    private final static QName _Persons_QNAME = new QName("http://www.example.org/OneIdSchema", "persons");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: domainapp.modules.base.dom.impl.generated
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Organisation }
     * 
     */
    public Organisation createOrganisation() {
        return new Organisation();
    }

    /**
     * Create an instance of {@link Success }
     * 
     */
    public Success createSuccess() {
        return new Success();
    }

    /**
     * Create an instance of {@link Algorithm }
     * 
     */
    public Algorithm createAlgorithm() {
        return new Algorithm();
    }

    /**
     * Create an instance of {@link Persons }
     * 
     */
    public Persons createPersons() {
        return new Persons();
    }

    /**
     * Create an instance of {@link Person }
     * 
     */
    public Person createPerson() {
        return new Person();
    }

    /**
     * Create an instance of {@link Effort }
     * 
     */
    public Effort createEffort() {
        return new Effort();
    }

    /**
     * Create an instance of {@link Reward }
     * 
     */
    public Reward createReward() {
        return new Reward();
    }

    /**
     * Create an instance of {@link Result }
     * 
     */
    public Result createResult() {
        return new Result();
    }

    /**
     * Create an instance of {@link Cooperation }
     * 
     */
    public Cooperation createCooperation() {
        return new Cooperation();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Aim }
     * 
     */
    public Aim createAim() {
        return new Aim();
    }

    /**
     * Create an instance of {@link Goal }
     * 
     */
    public Goal createGoal() {
        return new Goal();
    }

    /**
     * Create an instance of {@link Outcome }
     * 
     */
    public Outcome createOutcome() {
        return new Outcome();
    }

    /**
     * Create an instance of {@link Plan }
     * 
     */
    public Plan createPlan() {
        return new Plan();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Organisation }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/OneIdSchema", name = "organisation")
    public JAXBElement<Organisation> createOrganisation(Organisation value) {
        return new JAXBElement<Organisation>(_Organisation_QNAME, Organisation.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Success }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/OneIdSchema", name = "success")
    public JAXBElement<Success> createSuccess(Success value) {
        return new JAXBElement<Success>(_Success_QNAME, Success.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Algorithm }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/OneIdSchema", name = "algorithm")
    public JAXBElement<Algorithm> createAlgorithm(Algorithm value) {
        return new JAXBElement<Algorithm>(_Algorithm_QNAME, Algorithm.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Persons }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.example.org/OneIdSchema", name = "persons")
    public JAXBElement<Persons> createPersons(Persons value) {
        return new JAXBElement<Persons>(_Persons_QNAME, Persons.class, null, value);
    }

}
