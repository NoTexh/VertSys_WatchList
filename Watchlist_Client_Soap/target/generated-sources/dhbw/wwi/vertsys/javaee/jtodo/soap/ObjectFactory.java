
package dhbw.wwi.vertsys.javaee.jtodo.soap;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the dhbw.wwi.vertsys.javaee.jtodo.soap package. 
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

    private final static QName _FindMovieById_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMovieById");
    private final static QName _FindMovieByIdResponse_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMovieByIdResponse");
    private final static QName _FindMovies_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMovies");
    private final static QName _FindMoviesByUser_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMoviesByUser");
    private final static QName _FindMoviesByUserResponse_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMoviesByUserResponse");
    private final static QName _FindMoviesResponse_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "findMoviesResponse");
    private final static QName _InvalidCredentialsException_QNAME = new QName("http://soap.jtodo.javaee.vertsys.wwi.dhbw/", "InvalidCredentialsException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: dhbw.wwi.vertsys.javaee.jtodo.soap
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link FindMovieById }
     * 
     */
    public FindMovieById createFindMovieById() {
        return new FindMovieById();
    }

    /**
     * Create an instance of {@link FindMovieByIdResponse }
     * 
     */
    public FindMovieByIdResponse createFindMovieByIdResponse() {
        return new FindMovieByIdResponse();
    }

    /**
     * Create an instance of {@link FindMovies }
     * 
     */
    public FindMovies createFindMovies() {
        return new FindMovies();
    }

    /**
     * Create an instance of {@link FindMoviesByUser }
     * 
     */
    public FindMoviesByUser createFindMoviesByUser() {
        return new FindMoviesByUser();
    }

    /**
     * Create an instance of {@link FindMoviesByUserResponse }
     * 
     */
    public FindMoviesByUserResponse createFindMoviesByUserResponse() {
        return new FindMoviesByUserResponse();
    }

    /**
     * Create an instance of {@link FindMoviesResponse }
     * 
     */
    public FindMoviesResponse createFindMoviesResponse() {
        return new FindMoviesResponse();
    }

    /**
     * Create an instance of {@link InvalidCredentialsException }
     * 
     */
    public InvalidCredentialsException createInvalidCredentialsException() {
        return new InvalidCredentialsException();
    }

    /**
     * Create an instance of {@link Movie }
     * 
     */
    public Movie createMovie() {
        return new Movie();
    }

    /**
     * Create an instance of {@link Category }
     * 
     */
    public Category createCategory() {
        return new Category();
    }

    /**
     * Create an instance of {@link Date }
     * 
     */
    public Date createDate() {
        return new Date();
    }

    /**
     * Create an instance of {@link User }
     * 
     */
    public User createUser() {
        return new User();
    }

    /**
     * Create an instance of {@link Task }
     * 
     */
    public Task createTask() {
        return new Task();
    }

    /**
     * Create an instance of {@link Time }
     * 
     */
    public Time createTime() {
        return new Time();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMovieById }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMovieById }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMovieById")
    public JAXBElement<FindMovieById> createFindMovieById(FindMovieById value) {
        return new JAXBElement<FindMovieById>(_FindMovieById_QNAME, FindMovieById.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMovieByIdResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMovieByIdResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMovieByIdResponse")
    public JAXBElement<FindMovieByIdResponse> createFindMovieByIdResponse(FindMovieByIdResponse value) {
        return new JAXBElement<FindMovieByIdResponse>(_FindMovieByIdResponse_QNAME, FindMovieByIdResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMovies }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMovies }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMovies")
    public JAXBElement<FindMovies> createFindMovies(FindMovies value) {
        return new JAXBElement<FindMovies>(_FindMovies_QNAME, FindMovies.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMoviesByUser }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMoviesByUser }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMoviesByUser")
    public JAXBElement<FindMoviesByUser> createFindMoviesByUser(FindMoviesByUser value) {
        return new JAXBElement<FindMoviesByUser>(_FindMoviesByUser_QNAME, FindMoviesByUser.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMoviesByUserResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMoviesByUserResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMoviesByUserResponse")
    public JAXBElement<FindMoviesByUserResponse> createFindMoviesByUserResponse(FindMoviesByUserResponse value) {
        return new JAXBElement<FindMoviesByUserResponse>(_FindMoviesByUserResponse_QNAME, FindMoviesByUserResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FindMoviesResponse }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link FindMoviesResponse }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "findMoviesResponse")
    public JAXBElement<FindMoviesResponse> createFindMoviesResponse(FindMoviesResponse value) {
        return new JAXBElement<FindMoviesResponse>(_FindMoviesResponse_QNAME, FindMoviesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link InvalidCredentialsException }{@code >}
     * 
     * @param value
     *     Java instance representing xml element's value.
     * @return
     *     the new instance of {@link JAXBElement }{@code <}{@link InvalidCredentialsException }{@code >}
     */
    @XmlElementDecl(namespace = "http://soap.jtodo.javaee.vertsys.wwi.dhbw/", name = "InvalidCredentialsException")
    public JAXBElement<InvalidCredentialsException> createInvalidCredentialsException(InvalidCredentialsException value) {
        return new JAXBElement<InvalidCredentialsException>(_InvalidCredentialsException_QNAME, InvalidCredentialsException.class, null, value);
    }

}
