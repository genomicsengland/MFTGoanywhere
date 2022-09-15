@GoAnywhere
@RPDS-4825
Feature: Contract Research Organisation Feature

  Scenario Outline: Contract Research Organisation

    Given the user launches the GoAnywhere application
    And the user is logged into GoAnywhere
    And the user select Forms
    And the user selects Contract Research Organisation form
    And the user sets Contract UserGroup to <UserGroup>
    And the user sets File to <FilePath>
    And the user sets Contract PartcipantID to <ParticipantID>
    And the user sets Contract Description of file content to <Content>
    And the user sets Research Registry ID to <Registry ID>
    And the user sets Contract Transfer Request Justification to <Justification>
    And the user sets Contract Transfer Agreement to <Agreement>
    And the user submits Form
    Examples:
    |UserGroup|FilePath|ParticipantID|Content|Registry ID|Justification|Agreement|
    |GeCIP    |C:\\Projects\\MFTGoanywhere\\src\\test\\Resources\\Test.txt|C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AParticipantID.txt|TestContent|123|Justification|Yes|
    |Discovery Forum |C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AppConfig.txt|C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AParticipantID.txt|TestContent1|1234|Test Justification|Yes|
