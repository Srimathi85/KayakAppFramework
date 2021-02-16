Feature:Kayak_FlightFeature

  Scenario Outline:Kayak_Fight searching features
    Given User launches kayak page
    And Select the Flight option
    Then Kayak Flight page is displayed
    When Enter origin Airport "<Origin>"
    And Select origin Nearby Airport
  And  Enter Destination Airport "<Destination>"
    And Select Nearby Airports for destination
    And    Select  Departure and Return Dates
    And Click Search
    Then  The flight details for entered the Origin and Destination Details are displayed
Examples:
    |Origin|Destination|
    |Bengaluru (BLR)|San Francisco (SFO)|
    |San Francisco (SFO)|Chennai (MAA)  |
    |Chennai (MAA)  |San Francisco (SFO)|


