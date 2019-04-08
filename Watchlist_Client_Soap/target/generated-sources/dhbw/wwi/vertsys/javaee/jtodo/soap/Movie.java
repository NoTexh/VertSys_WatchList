
package dhbw.wwi.vertsys.javaee.jtodo.soap;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java-Klasse für movie complex type.
 * 
 * <p>Das folgende Schemafragment gibt den erwarteten Content an, der in dieser Klasse enthalten ist.
 * 
 * <pre>
 * &lt;complexType name="movie"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="category" type="{http://soap.jtodo.javaee.vertsys.wwi.dhbw/}category" minOccurs="0"/&gt;
 *         &lt;element name="creationDate" type="{http://soap.jtodo.javaee.vertsys.wwi.dhbw/}date" minOccurs="0"/&gt;
 *         &lt;element name="movieDescription" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="movieId" type="{http://www.w3.org/2001/XMLSchema}long"/&gt;
 *         &lt;element name="movieStatus" type="{http://soap.jtodo.javaee.vertsys.wwi.dhbw/}movieStatus" minOccurs="0"/&gt;
 *         &lt;element name="movieTitle" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="owner" type="{http://soap.jtodo.javaee.vertsys.wwi.dhbw/}user" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "movie", propOrder = {
    "category",
    "creationDate",
    "movieDescription",
    "movieId",
    "movieStatus",
    "movieTitle",
    "owner"
})
public class Movie {

    protected Category category;
    protected Date creationDate;
    protected String movieDescription;
    protected long movieId;
    @XmlSchemaType(name = "string")
    protected MovieStatus movieStatus;
    protected String movieTitle;
    protected User owner;

    /**
     * Ruft den Wert der category-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Legt den Wert der category-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Ruft den Wert der creationDate-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link Date }
     *     
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Legt den Wert der creationDate-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link Date }
     *     
     */
    public void setCreationDate(Date value) {
        this.creationDate = value;
    }

    /**
     * Ruft den Wert der movieDescription-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovieDescription() {
        return movieDescription;
    }

    /**
     * Legt den Wert der movieDescription-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovieDescription(String value) {
        this.movieDescription = value;
    }

    /**
     * Ruft den Wert der movieId-Eigenschaft ab.
     * 
     */
    public long getMovieId() {
        return movieId;
    }

    /**
     * Legt den Wert der movieId-Eigenschaft fest.
     * 
     */
    public void setMovieId(long value) {
        this.movieId = value;
    }

    /**
     * Ruft den Wert der movieStatus-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link MovieStatus }
     *     
     */
    public MovieStatus getMovieStatus() {
        return movieStatus;
    }

    /**
     * Legt den Wert der movieStatus-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link MovieStatus }
     *     
     */
    public void setMovieStatus(MovieStatus value) {
        this.movieStatus = value;
    }

    /**
     * Ruft den Wert der movieTitle-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMovieTitle() {
        return movieTitle;
    }

    /**
     * Legt den Wert der movieTitle-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMovieTitle(String value) {
        this.movieTitle = value;
    }

    /**
     * Ruft den Wert der owner-Eigenschaft ab.
     * 
     * @return
     *     possible object is
     *     {@link User }
     *     
     */
    public User getOwner() {
        return owner;
    }

    /**
     * Legt den Wert der owner-Eigenschaft fest.
     * 
     * @param value
     *     allowed object is
     *     {@link User }
     *     
     */
    public void setOwner(User value) {
        this.owner = value;
    }

}
