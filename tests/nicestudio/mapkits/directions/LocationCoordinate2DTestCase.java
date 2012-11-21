package nicestudio.mapkits.directions;


import org.junit.Assert;
import org.junit.Test;

public class LocationCoordinate2DTestCase {

	@Test
	public void testEquals() {
		LocationCoordinate2D l1 = new LocationCoordinate2D(22.638378,
				120.301878);
		
		LocationCoordinate2D l2 = new LocationCoordinate2D(22.638378,
				120.301878);

		LocationCoordinate2D l3 = new LocationCoordinate2D(12.638378,
				120.301878);

		Assert.assertEquals(l1, l2);
		Assert.assertFalse(l1.equals(l3));
	}

}
