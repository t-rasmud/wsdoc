/**
 * This endpoint has private publication scope.
 */

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.versly.rest.wsdoc.PublicationScope;
import org.versly.rest.wsdoc.RestApiMountPoint;

public class PublicationScopes {

    /** 
     * A controller with default publication scopes 
     */
    @RestApiMountPoint("/default")
    @RequestMapping("/api/v1")
    public static class DefaultController {

        @RequestMapping(value = "/public", method = RequestMethod.GET)
        public void pub() {
        }
    }

    /**
     * A controller with some public and some private scopes 
     */
    @RestApiMountPoint("/mixed")
    @RequestMapping("/api/v1")
    public static class MixedController {

        @PublicationScope(PublicationScope.PUBLIC)
        @RequestMapping(value = "/public", method = RequestMethod.GET)
        public void pub() {
        }

        @PublicationScope(PublicationScope.PRIVATE)
        @RequestMapping(value = "/private", method = RequestMethod.GET)
        public void priv() {
        }
    }

    /**
     * A controller with all private scopes. 
     */
    @RestApiMountPoint("/private")
    @RequestMapping("/api/v1")
    public static class PrivateController {

        @PublicationScope(PublicationScope.PRIVATE)
        @RequestMapping(value = "/private", method = RequestMethod.GET)
        public void priv() {
        }
    }

    /**
     * A controller that is private at the class level and containing one public method.
     */
    @PublicationScope(PublicationScope.PRIVATE)
    @RestApiMountPoint("/classpriv")
    @RequestMapping("/api/v1")
    public static class ClassPrivateController {

        @RequestMapping(value = "/private", method = RequestMethod.GET)
        public void priv() {
        }

        @PublicationScope(PublicationScope.PUBLIC)
        @RequestMapping(value = "/public", method = RequestMethod.GET)
        public void pub() {
        }
    }

    /**
     * Controller with a public endpoint whose path is a child of a private endpoint.
     * The rule here is that if a parent URL is not publised, the child URL will also
     * not be published.
     */
    @RestApiMountPoint("/nestedpublic")
    @RequestMapping("/api/v1")
    public static class NestedPublicController {

        @PublicationScope(PublicationScope.PRIVATE)
        @RequestMapping(value = "/private/foo", method = RequestMethod.GET)
        public void priv() {
        }

        @PublicationScope(PublicationScope.PUBLIC)
        @RequestMapping(value = "/private/foo/bar", method = RequestMethod.GET)
        public void pub() {
        }
    }

}

