/**
 * Copyright 2013 The Apache Software Foundation
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package ch.sentric;

import static org.junit.Assert.*;

import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;

import org.junit.Before;
import org.junit.Test;

/**
 * The {@link URL} test class.
 */
public class URLTest {

    private URL standardPlainUrl;
    private URL standardUrlWithPort;
    private URL standardUrlWithSubDomain;
    private URL urlWithSubDomainAndPath;
    private URL urlWithSpaceInPath;
    private URL urlWithSeveralSpacesInPath;
    private URL urlWithUnsortedQuery;
    private URL urlWithUnsortedQueryAndSpace;
    private URL urlWithUserAndPass;
    private URL urlWithHttpsSchemaAndFragment;
    private URL urlWithTrailingSlashInPathAndQuery;

    @Before
    public void setUp() throws Exception {
	this.standardPlainUrl = new URL("http://www.domain.com/");
	this.standardUrlWithPort = new URL("http://www.domain.com:80");
	this.standardUrlWithSubDomain = new URL("http://test.domain.com/");
	this.urlWithSubDomainAndPath = new URL("http://test.domain.com/test/path");
	this.urlWithSpaceInPath = new URL("http://www.domain.com/tes t/pat h");
	this.urlWithSeveralSpacesInPath = new URL("http://www.domain.com/tes    t/pat h");
	this.urlWithUnsortedQuery = new URL("http://www.domain.com/test/path.php?val=12&a=15&x=10");
	this.urlWithUnsortedQueryAndSpace = new URL("http://www.domain.com/te st/path.php?val=12&a=15&x=10");
	this.urlWithUserAndPass = new URL("http://user:pass@www.domain.com/test/path.php?val=12&a=15&x=10");
	this.urlWithHttpsSchemaAndFragment = new URL("https://www.domain.com/test/path.php?val=12&a=15&x=10#4");
	this.urlWithTrailingSlashInPathAndQuery = new URL("http://www.domain.com/test/path/?val=12&a=15&x=10");
    }

    @Test
    public void testGetAuthority() {
	assertEquals("www.domain.com", this.standardPlainUrl.getAuthority().getAsString());
	assertEquals("www.domain.com:80", this.standardUrlWithPort.getAuthority().getAsString());
	assertEquals("test.domain.com", this.standardUrlWithSubDomain.getAuthority().getAsString());
	assertEquals("test.domain.com", this.urlWithSubDomainAndPath.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithSpaceInPath.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithSeveralSpacesInPath.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithUnsortedQuery.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithUnsortedQueryAndSpace.getAuthority().getAsString());
	assertEquals("user:pass@www.domain.com", this.urlWithUserAndPass.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithHttpsSchemaAndFragment.getAuthority().getAsString());
	assertEquals("www.domain.com", this.urlWithTrailingSlashInPathAndQuery.getAuthority().getAsString());
    }

    @Test
    public void testGetFragment() {
	assertEquals(null, this.standardPlainUrl.getFragment());
	assertEquals(null, this.standardUrlWithPort.getFragment());
	assertEquals(null, this.standardUrlWithSubDomain.getFragment());
	assertEquals(null, this.urlWithSubDomainAndPath.getFragment());
	assertEquals(null, this.urlWithSpaceInPath.getFragment());
	assertEquals(null, this.urlWithSeveralSpacesInPath.getFragment());
	assertEquals(null, this.urlWithUnsortedQuery.getFragment());
	assertEquals(null, this.urlWithUnsortedQueryAndSpace.getFragment());
	assertEquals(null, this.urlWithUserAndPass.getFragment());
	assertEquals("4", this.urlWithHttpsSchemaAndFragment.getFragment());
	assertEquals(null, this.urlWithTrailingSlashInPathAndQuery.getFragment());
    }

    @Test
    public void testGetGivenInputUrl() {
	assertEquals("http://www.domain.com/", this.standardPlainUrl.getGivenInputUrl());
	assertEquals("http://www.domain.com:80", this.standardUrlWithPort.getGivenInputUrl());
	assertEquals("http://test.domain.com/", this.standardUrlWithSubDomain.getGivenInputUrl());
	assertEquals("http://test.domain.com/test/path", this.urlWithSubDomainAndPath.getGivenInputUrl());
	assertEquals("http://www.domain.com/tes t/pat h", this.urlWithSpaceInPath.getGivenInputUrl());
	assertEquals("http://www.domain.com/tes    t/pat h", this.urlWithSeveralSpacesInPath.getGivenInputUrl());
	assertEquals("http://www.domain.com/test/path.php?val=12&a=15&x=10", this.urlWithUnsortedQuery.getGivenInputUrl());
	assertEquals("http://www.domain.com/te st/path.php?val=12&a=15&x=10", this.urlWithUnsortedQueryAndSpace.getGivenInputUrl());
	assertEquals("http://user:pass@www.domain.com/test/path.php?val=12&a=15&x=10", this.urlWithUserAndPass.getGivenInputUrl());
	assertEquals("https://www.domain.com/test/path.php?val=12&a=15&x=10#4", this.urlWithHttpsSchemaAndFragment.getGivenInputUrl());
	assertEquals("http://www.domain.com/test/path/?val=12&a=15&x=10", this.urlWithTrailingSlashInPathAndQuery.getGivenInputUrl());
    }

    @Test
    public void getURIShouldReturnUriFromGivenUrl() throws URISyntaxException {
	assertEquals(this.standardPlainUrl.getGivenInputUrl(), this.standardPlainUrl.getURI().toString());
    }

    @Test
    public void testGetNormalizedUrl() {
	assertEquals("com.domain", this.standardPlainUrl.getNormalizedUrl());
	assertEquals("com.domain", this.standardUrlWithPort.getNormalizedUrl());
	assertEquals("com.domain.test", this.standardUrlWithSubDomain.getNormalizedUrl());
	assertEquals("com.domain.test/test/path", this.urlWithSubDomainAndPath.getNormalizedUrl());
	assertEquals("com.domain/tes+t/pat+h", this.urlWithSpaceInPath.getNormalizedUrl());
	assertEquals("com.domain/tes++++t/pat+h", this.urlWithSeveralSpacesInPath.getNormalizedUrl());
	assertEquals("com.domain/test/path.php?a=15&val=12&x=10", this.urlWithUnsortedQuery.getNormalizedUrl());
	assertEquals("com.domain/te+st/path.php?a=15&val=12&x=10", this.urlWithUnsortedQueryAndSpace.getNormalizedUrl());
	assertEquals("com.domain/test/path.php?a=15&val=12&x=10", this.urlWithUserAndPass.getNormalizedUrl());
	assertEquals("com.domain/test/path?a=15&val=12&x=10", this.urlWithTrailingSlashInPathAndQuery.getNormalizedUrl());
    }

    @Test
    public void testGetPath() {
	assertEquals("", this.standardPlainUrl.getPath().getAsString());
	assertEquals("", this.standardUrlWithPort.getPath().getAsString());
	assertEquals("", this.standardUrlWithSubDomain.getPath().getAsString());
	assertEquals("/test/path", this.urlWithSubDomainAndPath.getPath().getAsString());
	assertEquals("/tes t/pat h", this.urlWithSpaceInPath.getPath().getAsString());
	assertEquals("/tes    t/pat h", this.urlWithSeveralSpacesInPath.getPath().getAsString());
	assertEquals("/test/path.php", this.urlWithUnsortedQuery.getPath().getAsString());
	assertEquals("/te st/path.php", this.urlWithUnsortedQueryAndSpace.getPath().getAsString());
	assertEquals("/test/path.php", this.urlWithUserAndPass.getPath().getAsString());
	assertEquals("/test/path.php", this.urlWithHttpsSchemaAndFragment.getPath().getAsString());
	assertEquals("/test/path", this.urlWithTrailingSlashInPathAndQuery.getPath().getAsString());
    }

    @Test
    public void testGetQuery() {
	assertEquals("", this.standardPlainUrl.getQuery().getAsString());
	assertEquals("", this.standardUrlWithPort.getQuery().getAsString());
	assertEquals("", this.standardUrlWithSubDomain.getQuery().getAsString());
	assertEquals("", this.urlWithSubDomainAndPath.getQuery().getAsString());
	assertEquals("", this.urlWithSpaceInPath.getQuery().getAsString());
	assertEquals("", this.urlWithSeveralSpacesInPath.getQuery().getAsString());
	assertEquals("val=12&a=15&x=10", this.urlWithUnsortedQuery.getQuery().getAsString());
	assertEquals("a=15&val=12&x=10", this.urlWithUnsortedQuery.getQuery().getAsSortedString());
	assertEquals("val=12&a=15&x=10", this.urlWithUnsortedQueryAndSpace.getQuery().getAsString());
	assertEquals("a=15&val=12&x=10", this.urlWithUnsortedQueryAndSpace.getQuery().getAsSortedString());
	assertEquals("val=12&a=15&x=10", this.urlWithUserAndPass.getQuery().getAsString());
	assertEquals("a=15&val=12&x=10", this.urlWithUserAndPass.getQuery().getAsSortedString());
	assertEquals("val=12&a=15&x=10", this.urlWithHttpsSchemaAndFragment.getQuery().getAsString());
	assertEquals("a=15&val=12&x=10", this.urlWithHttpsSchemaAndFragment.getQuery().getAsSortedString());
	assertEquals("val=12&a=15&x=10", this.urlWithTrailingSlashInPathAndQuery.getQuery().getAsString());
	assertEquals("a=15&val=12&x=10", this.urlWithTrailingSlashInPathAndQuery.getQuery().getAsSortedString());
    }

    @Test
    public void testGetRepairedUrl() {
	assertEquals("http://www.domain.com", this.standardPlainUrl.getRepairedUrl());
	assertEquals("http://www.domain.com:80", this.standardUrlWithPort.getRepairedUrl());
	assertEquals("http://test.domain.com", this.standardUrlWithSubDomain.getRepairedUrl());
	assertEquals("http://test.domain.com/test/path", this.urlWithSubDomainAndPath.getRepairedUrl());
	assertEquals("http://www.domain.com/tes+t/pat+h", this.urlWithSpaceInPath.getRepairedUrl());
	assertEquals("http://www.domain.com/tes++++t/pat+h", this.urlWithSeveralSpacesInPath.getRepairedUrl());
	assertEquals("http://www.domain.com/test/path.php?val=12&a=15&x=10", this.urlWithUnsortedQuery.getRepairedUrl());
	assertEquals("http://www.domain.com/te+st/path.php?val=12&a=15&x=10", this.urlWithUnsortedQueryAndSpace.getRepairedUrl());
	assertEquals("http://user:pass@www.domain.com/test/path.php?val=12&a=15&x=10", this.urlWithUserAndPass.getRepairedUrl());
	assertEquals("https://www.domain.com/test/path.php?val=12&a=15&x=10#4", this.urlWithHttpsSchemaAndFragment.getRepairedUrl());
	assertEquals("http://www.domain.com/test/path?val=12&a=15&x=10", this.urlWithTrailingSlashInPathAndQuery.getRepairedUrl());
    }

    @Test
    public void testGetScheme() {
	assertEquals("http", this.standardPlainUrl.getScheme());
	assertEquals("http", this.standardUrlWithPort.getScheme());
	assertEquals("http", this.standardUrlWithSubDomain.getScheme());
	assertEquals("http", this.urlWithSubDomainAndPath.getScheme());
	assertEquals("http", this.urlWithSpaceInPath.getScheme());
	assertEquals("http", this.urlWithSeveralSpacesInPath.getScheme());
	assertEquals("http", this.urlWithUnsortedQuery.getScheme());
	assertEquals("http", this.urlWithUnsortedQueryAndSpace.getScheme());
	assertEquals("http", this.urlWithUserAndPass.getScheme());
	assertEquals("https", this.urlWithHttpsSchemaAndFragment.getScheme());
    }

    @Test
    public void uriConstructor() throws MalformedURLException, URISyntaxException {
	this.standardPlainUrl = new URL(new URI("http://www.domain.com/"));
	assertEquals("http://www.domain.com/", this.standardPlainUrl.getGivenInputUrl());
	assertEquals("http://www.domain.com", this.standardPlainUrl.getRepairedUrl());
	assertEquals("", this.standardPlainUrl.getQuery().getAsString());
	assertEquals("", this.standardPlainUrl.getPath().getAsString());
	assertEquals("com.domain", this.standardPlainUrl.getNormalizedUrl());
	assertEquals(null, this.standardPlainUrl.getFragment());
	assertEquals("www.domain.com", this.standardPlainUrl.getAuthority().getAsString());
    }

    @Test
    public void getUriShouldRemoveAllFragments() throws URISyntaxException, MalformedURLException {
	try {
	    final URI uri = new URL("http://www.spiegel.de/sport/fussball/0,1518,558100,00.html#BLREL-1124499-1-2010/11#ref=rss").getURI();
	    assertNull(uri.getFragment());
	} catch (final URISyntaxException e) {
	    fail("getUri() should not throw URISyntaxException when url contains two fragments");
	}
    }

    @Test
    public void getUriShouldNotThrowEceptionWhenUrlContainsMoreThanOneFragment() throws URISyntaxException, MalformedURLException {
	try {
	    final URI uri = new URL("http://www.spiegel.de/sport/fussball/0,1518,558100,00.html#BLREL-1124499-1-2010/11#ref=rss#article_38873#feed_383882").getURI();
	    assertNull(uri.getFragment());
	} catch (final URISyntaxException e) {
	    fail("getUri() should not throw URISyntaxException when url contains more than one fragment");
	}
    }

    @Test
    public void getUriShouldNotThrowEceptionWhenUrlContainsOneFragment() throws URISyntaxException, MalformedURLException {
	try {
	    final URI uri = new URL("http://www.spiegel.de/sport/fussball/0,1518,558100,00.html#BLREL-1124499-1-2010/11").getURI();
	    assertNull(uri.getFragment());
	} catch (final URISyntaxException e) {
	    fail("getUri() should not throw URISyntaxException when url contains one fragment");
	}
    }

    @Test
    public void getUriShouldNotThrowEceptionWhenUrlContainsNoFragment() throws URISyntaxException, MalformedURLException {
	try {
	    final URI uri = new URL("http://www.spiegel.de/sport/fussball/0,1518,558100,00.html").getURI();
	    assertNull("getFragment() expected to be null when url does not contain a '#'", uri.getFragment());
	} catch (final URISyntaxException e) {
	    fail("getUri() should not throw URISyntaxException when url contains no fragment");
	}
    }
    
    @Test
    public void testGetNutchNormalizedUrl() throws MalformedURLException {
	assertEquals("gov.ecfr.www:http/cgi-bin/text-idx?node=49:6.1.2.3.38.2&rgn=div6/", new URL("http://www.ecfr.gov/cgi-bin/text-idx?SID=ffba705dd59401932f2839cd6da55ca1&node=49:6.1.2.3.38.2&rgn=div6/").getNutchNormalizedUrl());
	//assertEquals("http://www.domain.com:80", this.standardUrlWithPort.getRepairedUrl());
    }
    // http://store.thetimes.co.uk/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61
    @Test
    public void testGetNutchNormalizedUrl2() throws MalformedURLException {
	assertEquals("uk.co.thetimes.store:http/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61", new URL("http://store.thetimes.co.uk/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61").getNutchNormalizedUrl());
	// http://store.thetimes.co.uk/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61
    }
    
    @Test
    public void testGetNutchNormalizedHttpsUrl() throws MalformedURLException {
	assertEquals("gov.data.api:https/regulations/v3/download?api_key=4uetGHQda2lTEBZ14yPtPmL6LuACMGI4wWog4aCy&contentType=html&documentId=NHTSA-2010-0132-0034", new URL("https://api.data.gov/regulations/v3/download?api_key=4uetGHQda2lTEBZ14yPtPmL6LuACMGI4wWog4aCy&contentType=html&documentId=NHTSA-2010-0132-0034").getNutchNormalizedUrl());
	// http://store.thetimes.co.uk/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61
    }
    
    @Test
    public void testGetNutchNormalizedShebangUrl() throws MalformedURLException {
	assertEquals("gov.regulations.www:http/#!documentDetail;D=NHTSA-2012-0120-0001", new URL("http://www.regulations.gov/#!documentDetail;D=NHTSA-2012-0120-0001").getNutchNormalizedUrl());
	// http://store.thetimes.co.uk/?ILC=INT-TNL_The_Times-teaser-image-7_01_2013_-61
    }
    
    //http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2014.174.01.0028.01.ENG
    @Test
    public void testGetNutchNormalizedQuestionSlant() throws MalformedURLException {
	assertEquals("eu.europa.eur-lex:http/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2014.174.01.0028.01.ENG", new URL("http://eur-lex.europa.eu/legal-content/EN/TXT/?uri=uriserv:OJ.L_.2014.174.01.0028.01.ENG").getNutchNormalizedUrl());
    }
    
}
