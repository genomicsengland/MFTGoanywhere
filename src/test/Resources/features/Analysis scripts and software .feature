@GoAnywhere
@GoAnywhere_2
Feature: Contract Research Organisation Feature

  Scenario Outline: Contract Research Organisation scenario

    Given the user launches the GoAnywhere application
    And the user is logged into GoAnywhere
    And the user select Forms
    And the user selects AnalysisScriptsAndSoftware form
    And the user sets UserGroup to <UserGroup>
    And the user sets File to <FilePath>
    And the user sets Description of file content to <Content>
    And the user sets Research Registry ID to <Registry ID>
    And the user sets Transfer Request Justification to <Justification>
    And the user sets Programming Language to <Language>
    And the user sets Transfer Agreement to <Agreement>
    And the user submits Form
    Examples:
    |UserGroup|FilePath|Content|Registry ID|Justification|Language|Agreement|
    |GeCIP    |C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AppConfig.txt|TestContent|123|Justification|Python|Yes|
