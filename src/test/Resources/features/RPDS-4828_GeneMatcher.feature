@GoAnywhere
@RPDS-4828

Feature: Gene Matcher Feature

  Scenario Outline: Gene Matcher

    Given the user launches the GoAnywhere application
    And the user is logged into GoAnywhere
    And the user select Forms
    And the user selects GeneMatcher request form
    And the user sets GeneMatcher UserGroup to <UserGroup>
    And the user sets GeneMatcher Gene Symbol to <GeneSymbol>
    And the user sets GeneMatcher Zygosity to <Zygosity>
    And the user sets GeneMatcher Function to <Function>
    And the user sets GeneMatcher Inheritance model to <InheritanceModel>
    And the user sets GeneMatcher Phenotype term to <PhenotypeTerm>
    And the user sets GeneMatcher PartcipantID to <ParticipantID>
    And the user sets GeneMatcher Transfer Agreement to <Agreement>
    And the user submits Form

    Examples:
    |UserGroup|GeneSymbol|Zygosity|Function|InheritanceModel|PhenotypeTerm|ParticipantID|Agreement|
   |GeCIP    |Test|Heterozygous|Frameshift deletion|Autosomal recessive - compound heterozygous|Test|C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AParticipantID.txt|Yes|
   |Discovery Forum |Test1|Homozygous|Frameshift insertion|Autosomal recessive - homozygous|Test1   |C:\\Users\\Shipra.Sehgal\\OneDrive - Genomics England Ltd\\Desktop\\AParticipantID.txt|Yes|
