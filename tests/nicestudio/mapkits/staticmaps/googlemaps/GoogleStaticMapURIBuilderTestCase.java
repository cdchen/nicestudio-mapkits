package nicestudio.mapkits.staticmaps.googlemaps;

import junit.framework.Assert;
import nicestudio.mapkits.directions.LocationCoordinate2D;

import org.junit.Before;
import org.junit.Test;

public class GoogleStaticMapURIBuilderTestCase {

	private GoogleStaticMapURIBuilder<LocationCoordinate2D> builder;

	@Before
	public void setUp() {
		builder = new GoogleStaticMapURIBuilder<LocationCoordinate2D>();
	}

	@Test
	public void testNoEmptyParams() {
		Assert.assertEquals("http://maps.google.com/maps/api/staticmap",
				builder.toString());
	}

	@Test
	public void testSetSize() {
		builder.setSize(100, 100);
		Assert.assertEquals(
				"http://maps.google.com/maps/api/staticmap?size=100x100",
				builder.toString());
	}

	@Test(expected = GoogleStaticMapURIBuilder.InvalidValueException.class)
	public void testSetSizeWidthLessThanMin() {
		builder.setSize(-1, 0);
	}

	@Test(expected = GoogleStaticMapURIBuilder.InvalidValueException.class)
	public void testSetSizeWidthGreatThanMax() {
		builder.setSize(GoogleStaticMapURIBuilder.SIZE_WIDTH_MAX + 1, 0);
	}

	@Test(expected = GoogleStaticMapURIBuilder.InvalidValueException.class)
	public void testSetSizeHeightLessThanMin() {
		builder.setSize(100, -1);
	}

	@Test(expected = GoogleStaticMapURIBuilder.InvalidValueException.class)
	public void testSetSizeHeightGreatThanMax() {
		builder.setSize(100, GoogleStaticMapURIBuilder.SIZE_HEIGHT_MAX + 1);
	}

	@Test
	public void testAddSingleMarkers() {
		builder.addMarker(new LocationCoordinate2D(22.638378, 120.301878));
		Assert.assertEquals(
				"http://maps.google.com/maps/api/staticmap?markers=22.638378%2C120.301878",
				builder.toString());
	}

	@Test
	public void testAddMultipleMarkers() {
		builder.addMarker(new LocationCoordinate2D(22.638378, 120.301878),
				new LocationCoordinate2D(22.638378, 120.301878),
				new LocationCoordinate2D(22.638378, 120.301878));
//		System.out.println(builder);
		Assert.assertEquals(
				"http://maps.google.com/maps/api/staticmap?markers=22.638378%2C120.301878%7C22.638378%2C120.301878%7C22.638378%2C120.301878",
				builder.toString());
	}

}
