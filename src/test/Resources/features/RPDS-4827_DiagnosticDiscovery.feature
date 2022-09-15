@GoAnywhere
@RPDS-4827

Feature: Diagnostic Discovery Feature

  Scenario Outline: Diagnostic Discovery scenario

    Given the user launches the GoAnywhere application
    And the user is logged into GoAnywhere
    And the user select Forms
    And the user selects Diagnostic Discovery form
    And the user sets Diagnostic UserGroup to <UserGroup>
    And the user sets Diagnostic Research Registry ID to <Registry ID>
    And the user sets Diagnostic expertise to <Expertise>
    And the user sets Diagnostic contact with clinical team to <Contact>
    And user sets Diagnostic report potentially diagnostic variant to <Report>
    And user sets Diagnostic participant ids and variants to <PartcipantIDsAndVariants>
    And user sets Diagnostic Genome build to <GenomeBuild>
    And the user submits Form

    Examples:
    |UserGroup|Registry ID|Expertise|Contact|Report|PartcipantIDsAndVariants|GenomeBuild|
    |GeCIP    |123|Clinician|Yes|Yes|500000009, CFTR, 7:117530975:G:C |GRCh37|
    |Discovery Forum |Test|Bioinformatician|No|Yes|600000001, CFTR, 7:117531033:GC:G |GRCh38|
