//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2017.09.17 at 09:22:29 PM AEST 
//


package au.org.cooperation.modules.base.dom.impl.generated;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for Person complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="Person"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="family-name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="given-name" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="date-of-birth" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="effort" type="{http://www.example.org/OneIdSchema}Effort" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="reward" type="{http://www.example.org/OneIdSchema}Reward" maxOccurs="unbounded" minOccurs="0"/&gt;
 *         &lt;element name="algorithm" type="{http://www.example.org/OneIdSchema}Algorithm" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Person", propOrder = {
    "familyName",
    "givenName",
    "dateOfBirth",
    "effort",
    "reward",
    "algorithm"
})
public class Person {

    @XmlElement(name = "family-name", required = true)
    protected String familyName;
    @XmlElement(name = "given-name", required = true)
    protected String givenName;
    @XmlElement(name = "date-of-birth")
    protected String dateOfBirth;
    protected List<Effort> effort;
    protected List<Reward> reward;
    protected Algorithm algorithm;

    /**
     * Gets the value of the familyName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFamilyName() {
        return familyName;
    }

    /**
     * Sets the value of the familyName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFamilyName(String value) {
        this.familyName = value;
    }

    /**
     * Gets the value of the givenName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGivenName() {
        return givenName;
    }

    /**
     * Sets the value of the givenName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGivenName(String value) {
        this.givenName = value;
    }

    /**
     * Gets the value of the dateOfBirth property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the value of the dateOfBirth property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDateOfBirth(String value) {
        this.dateOfBirth = value;
    }

    /**
     * Gets the value of the effort property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the effort property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEffort().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Effort }
     * 
     * 
     */
    public List<Effort> getEffort() {
        if (effort == null) {
            effort = new ArrayList<Effort>();
        }
        return this.effort;
    }

    /**
     * Gets the value of the reward property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reward property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReward().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reward }
     * 
     * 
     */
    public List<Reward> getReward() {
        if (reward == null) {
            reward = new ArrayList<Reward>();
        }
        return this.reward;
    }

    /**
     * Gets the value of the algorithm property.
     * 
     * @return
     *     possible object is
     *     {@link Algorithm }
     *     
     */
    public Algorithm getAlgorithm() {
        return algorithm;
    }

    /**
     * Sets the value of the algorithm property.
     * 
     * @param value
     *     allowed object is
     *     {@link Algorithm }
     *     
     */
    public void setAlgorithm(Algorithm value) {
        this.algorithm = value;
    }

}
