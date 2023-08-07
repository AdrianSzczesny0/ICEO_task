Feature: Timeseries tests

  Scenario Outline: Verify /timeseries with correct token but different REST methods and dates
    Given I set "<StartDate>" and "<EndDate>"
    When I use "<Method>" request
    Then I get status code <Code>
    And Response contains "<Message>"
    Examples:
      | StartDate  | EndDate    | Code | Method | Message                            |
      | 2023-08-01 | 2023-08-01 | 200  | GET    | rates                              |
      |            | 2023-08-01 | 400  | GET    | no_timeframe_supplied              |
      | 2023-08-01 |            | 400  | GET    | no_timeframe_supplied              |
      |            |            | 400  | GET    | no_timeframe_supplied              |
      | 2023-13-01 | 2023-08-01 | 400  | GET    | invalid_start_date                 |
      | 2023-08-01 | 2023-13-01 | 400  | GET    | invalid_end_date                   |
      | 2023-08-01 | 2023-08-01 | 403  | POST   | You cannot consume this service    |
      | 2023-08-01 | 2023-08-01 | 404  | PUT    | no Route matched with those values |
      | 2023-08-01 | 2023-08-01 | 404  | PATCH  | no Route matched with those values |
      | 2023-08-01 | 2023-08-01 | 404  | DELETE | no Route matched with those values |


  Scenario Outline: Verify timeseries route and access
    Given I set request apiKey and route to "<Route>","<ApiKey>"
    When I use "<Method>" request
    Then I get status code <Code>
    And Response contains "<Message>"
    Examples:
      | Route       | ApiKey                          | Code | Method | Message                            |
      | /timeseries | VAg2sKX96pZQ8luOnU6VV14GM0DfJP6 | 401  | GET    | Invalid authentication credentials |
      | /timeseries |                                 | 401  | GET    | No API key found in request        |
      | timeseries  | 68798asdagjk*AS&Da8absfasd123n1 | 404  | GET    | no Route matched with those values |
      |             | njfd89a0das891213cdfas123mdfk9  | 404  | GET    | no Route matched with those values |

  #NOTICE!  -  we are not using working API key in example table. its safer to get it from getter Method like in scenario before.