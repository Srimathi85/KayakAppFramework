Feature:Kayak_FlightFeature
Background:
  Given User launches kayak page
  And Select the Flight option
  Scenario Outline:Kayak_Fight searching features without selecting Nearby Airport

    Then Kayak Flight page is displayed
    When Enter origin Airport "<Origin>"
  And  Enter Destination Airport "<Destination>"
    And    Select  Departure and Return Dates
    And Click Search
    Then  The flight details for entered the Origin and Destination Details are displayed
Examples:
    |Origin|Destination|
    |Bengaluru (BLR)|San Francisco (SFO)|
    |San Francisco (SFO)|Chennai (MAA)|
    |New York (NYC)|New Delhi (DEL)|

  Scenario Outline:Kayak_Fight searching features with selecting Nearby Airport

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
      |San Francisco (SFO)|Chennai (MAA)|
      |New York (NYC)|New Delhi (DEL)|


