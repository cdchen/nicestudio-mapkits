package nicestudio.mapkits.directions.googlemaps;

import nicestudio.mapkits.directions.LocationCoordinate2D;
import nicestudio.mapkits.directions.models.DirectionLeg;
import nicestudio.mapkits.directions.models.DirectionResult;
import nicestudio.mapkits.directions.models.DirectionRoute;
import nicestudio.mapkits.directions.models.DirectionStep;
import nicestudio.mapkits.directions.models.DirectionTravelMode;
import nicestudio.mapkits.directions.models.DirectionValue;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class GoogleDirectionParser {

	private static DirectionValue parseValue(JSONObject jsonObject)
			throws JSONException {
		DirectionValue result = new DirectionValue();
		result.setText(jsonObject.getString("text"));
		result.setValue(jsonObject.getDouble("value"));
		return result;
	}

	private static LocationCoordinate2D parseLocation(JSONObject jsonObject)
			throws JSONException {
		LocationCoordinate2D result = new LocationCoordinate2D();
		result.setLatitude(jsonObject.getDouble("lat"));
		result.setLongitude(jsonObject.getDouble("lng"));
		return result;
	}

	public static DirectionResult parse(String content) throws JSONException {
		
		DirectionResult directionResult = new DirectionResult();

		synchronized (content) {
			JSONObject responseJSON = new JSONObject(content);

			directionResult.setStatus(responseJSON.getString("status"));

			JSONArray jsonRoutes = responseJSON.getJSONArray("routes");
			for (int routeIndex = 0; routeIndex < jsonRoutes.length(); routeIndex++) {
				JSONObject jsonRoute = jsonRoutes.getJSONObject(routeIndex);

				DirectionRoute directionRoute = new DirectionRoute(
						directionResult);

				directionRoute.setCopyrights(jsonRoute.getString("copyrights"));

				directionRoute.setOverviewPolyline(jsonRoute.getJSONObject(
						"overview_polyline").getString("points"));

				directionRoute.setSummary(jsonRoute.getString("summary"));

				JSONArray jsonRouteWarnings = jsonRoute
						.getJSONArray("warnings");
				for (int jsonRouteWarningIndex = 0; jsonRouteWarningIndex < jsonRouteWarnings
						.length(); jsonRouteWarningIndex++) {
					directionRoute.getWarnings().add(
							jsonRouteWarnings.getString(jsonRouteWarningIndex));
				}

				JSONArray jsonLegs = jsonRoute.getJSONArray("legs");
				for (int jsonLegIndex = 0; jsonLegIndex < jsonLegs.length(); jsonLegIndex++) {
					JSONObject jsonLeg = jsonLegs.getJSONObject(jsonLegIndex);

					DirectionLeg directionLeg = new DirectionLeg(directionRoute);

					directionLeg.setDistance(parseValue(jsonLeg
							.getJSONObject("distance")));

					directionLeg.setDuration(parseValue(jsonLeg
							.getJSONObject("duration")));

					directionLeg.setStartAddress(jsonLeg
							.getString("start_address"));

					directionLeg
							.setEndAddress(jsonLeg.getString("end_address"));

					directionLeg.setStartLocation(parseLocation(jsonLeg
							.getJSONObject("start_location")));

					directionLeg.setEndLocation(parseLocation(jsonLeg
							.getJSONObject("end_location")));

					JSONArray jsonSteps = jsonLeg.getJSONArray("steps");
					for (int jsonStepIndex = 0; jsonStepIndex < jsonSteps
							.length(); jsonStepIndex++) {
						JSONObject jsonStep = jsonSteps
								.getJSONObject(jsonStepIndex);

						DirectionStep directionStep = new DirectionStep(
								directionLeg);

						directionStep.setDistance(parseValue(jsonStep
								.getJSONObject("distance")));

						directionStep.setDuration(parseValue(jsonStep
								.getJSONObject("duration")));

						directionStep.setStartLocation(parseLocation(jsonStep
								.getJSONObject("start_location")));

						directionStep.setEndLocation(parseLocation(jsonStep
								.getJSONObject("end_location")));

						directionStep.setHtmlInstruction(jsonStep
								.getString("html_instructions"));

						directionStep.setPolyline(jsonStep.getJSONObject(
								"polyline").getString("points"));

						directionStep.setTravelMode(DirectionTravelMode
								.valueOf(jsonStep.getString("travel_mode")));

						directionLeg.getSteps().add(directionStep);
					}

					directionRoute.getLegs().add(directionLeg);
				}

				directionResult.getRoutes().add(directionRoute);
			}
		}
		return directionResult;
	}
}
