@GoAnywhere
@RPDS-4826
Feature: RE Findings Form

  Scenario Outline: RE Findings scenario

    Given the user launches the GoAnywhere application
    And the user is logged into GoAnywhere
    And the user select Forms
    And the user selects REFindings form
    And the user sets REFindings UserGroup to <UserGroup>
    And the user sets File to <FilePath>
    And the user sets Contract PartcipantID to <ParticipantID>
    And the user sets REFindings Description of file content to <Content>
    And the user sets Research Registry ID to <Registry ID>
    And the user sets REFindings Disease Type to <DiseaseType>
    And the user sets REFindings Aim of Export to <Aim>
    And the user sets REFindings Transfer Request Justification to <Justification>
    And the user sets REFindings Transfer Agreement to <Agreement>
    And the user submits Form
    Examples:
      |UserGroup|FilePath|ParticipantID|Content|Registry ID|DiseaseType|Aim|Justification|Agreement|
      |GeCIP    |C:\\Projects\\MFTGoanywhere\\src\\test\\Resources\\Test.txt|C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AParticipantID.txt|TestContent|123|Cancer|Publication|Justification|Yes|
