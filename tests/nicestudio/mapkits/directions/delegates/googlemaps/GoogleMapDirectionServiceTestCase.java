package nicestudio.mapkits.directions.delegates.googlemaps;

import java.util.ArrayList;
import java.util.List;

import nicestudio.mapkits.directions.LocationCoordinate2D;
import nicestudio.mapkits.directions.googlemaps.GoogleMapDirectionService;
import nicestudio.mapkits.directions.models.DirectionLeg;
import nicestudio.mapkits.directions.models.DirectionResult;
import nicestudio.mapkits.directions.models.DirectionRoute;
import nicestudio.mapkits.directions.models.DirectionStep;
import nicestudio.mapkits.directions.models.DirectionTravelMode;

import org.apache.commons.lang3.StringUtils;
import org.apache.http.client.utils.URIBuilder;
import org.junit.Assert;
import org.junit.Test;

public class GoogleMapDirectionServiceTestCase {

	private LocationCoordinate2D start = new LocationCoordinate2D(22.638378,
			120.301878);

	private LocationCoordinate2D end = new LocationCoordinate2D(25.047924,
			121.517081);

	GoogleMapDirectionService<LocationCoordinate2D> service = new GoogleMapDirectionService<LocationCoordinate2D>();

	@Test
	public void testCreateURIBuilder() {
		List<LocationCoordinate2D> locations = new ArrayList<LocationCoordinate2D>();
		locations.add(start);
		locations.add(end);

		try {
			URIBuilder builder = service.createURIBuilder(locations, null);

			Assert.assertEquals(
					"http://maps.googleapis.com/maps/api/directions/json?destination=25.047924%2C121.517081&origin=22.638378%2C120.301878&sensor=false&unit=metric&language=zh-TW&mode=DRIVING",
					builder.toString());

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

	}

	@Test
	public void testGetDirectionResult() {

		List<LocationCoordinate2D> locations = new ArrayList<LocationCoordinate2D>();
		locations.add(start);
		locations.add(end);

		DirectionResult directionResult = null;

		try {
			directionResult = service.getDirectionResult(locations,
					DirectionTravelMode.DRIVING, false);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertNotNull(directionResult);
		Assert.assertEquals("OK", directionResult.getStatus());
	}

	@Test
	public void testStructure() {
		List<LocationCoordinate2D> locations = new ArrayList<LocationCoordinate2D>();
		locations.add(start);
		locations.add(end);

		DirectionResult directionResult = null;

		try {
			directionResult = service.getDirectionResult(locations,
					DirectionTravelMode.DRIVING, false);

		} catch (Exception e) {
			Assert.fail(e.getMessage());
		}

		Assert.assertEquals(1, directionResult.getRoutes().size());
		DirectionRoute directionRoute = directionResult.getRoutes().get(0);
		Assert.assertNotNull(directionRoute);
		Assert.assertTrue(directionRoute.getLegs().size() > 0);
		Assert.assertFalse(StringUtils.isEmpty(directionRoute
				.getOverviewPolyline()));

		for (DirectionLeg leg : directionRoute.getLegs()) {
			Assert.assertEquals(directionRoute, leg.getDirectionRoute());

			Assert.assertNotNull(leg);
			Assert.assertTrue(leg.getSteps().size() > 0);
			Assert.assertNotNull(leg.getStartLocation());
			Assert.assertNotNull(leg.getEndLocation());

			for (DirectionStep step : leg.getSteps()) {
				Assert.assertEquals(leg, step.getDirectionLeg());

				Assert.assertNotNull(step);
				Assert.assertNotNull(step.getStartLocation());
				Assert.assertNotNull(step.getEndLocation());
				Assert.assertFalse(StringUtils.isEmpty(step
						.getHtmlInstruction()));
			}

			Assert.assertTrue(leg.getHtmlInstructions().size() > 0);
			Assert.assertEquals(leg.getSteps().size(), leg
					.getHtmlInstructions().size());
		}
	}
}
