Feature: Validating Place API's

@AddPlace @Regression
Scenario Outline: Verify if place is being succefully added using AddPlaceAPI
	Given Add place Payload "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"
	And "scope" in response body is "APP"
	Then verify place_Id created maps to "<name>" using "getPlaceAPI"
	
Examples: 
	|name	| language	|	address			|
	|ABC	|English	|9028 197 st Hollis	|
	|BBHouse|Spanish	|Bangladesh, dhaka	|


@DeletePlace @Regression
Scenario: verify id Delete place functionality is working
	
	Given DeletePlace Payload
	When user calls "deletePlaceAPI" with "POST" http request
	Then the API call got success with status code 200
	And "status" in response body is "OK"