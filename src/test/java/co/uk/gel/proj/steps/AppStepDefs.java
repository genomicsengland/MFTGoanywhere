package co.uk.gel.proj.steps;

import co.uk.gel.config.SeleniumDriver;
import co.uk.gel.proj.pages.Pages;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import gherkin.lexer.Th;

public class AppStepDefs extends Pages {

    public AppStepDefs(SeleniumDriver driver) {
        super(driver);
    }


    @Given("^the user launches the GoAnywhere application$")
    public void theUserLaunchesTheGoAnywhereApplication() {
        appHomePage.navigatesToGoAnywhere();
    }

    @And("^the user is logged into GoAnywhere$")
    public void theUserIsLoggedIntoGoAnywhere() {
        appHomePage.loginToGoAnywhere();
    }

    @And("the user select Forms")
    public void theUserSelectForms() {
        analysisScriptAndSoftwarePage.selectAvailableForms();
    }

    @And("the user selects AnalysisScriptsAndSoftware form")
    public void theUserSelectsAnalysisScriptsAndSoftwareForm() {
        analysisScriptAndSoftwarePage.selectAnalysisScriptsAndSoftwareForms();
    }

    @And("^the user sets UserGroup to (.+)$")
    public void theUserSetsUserGroupToUserGroup(String value) throws Throwable {
        analysisScriptAndSoftwarePage.setUserGroupValue(value);
    }

    @And("^the user sets File to (.+)$")
    public void theUserSetsFileToFilePath(String value) throws Throwable {
        analysisScriptAndSoftwarePage.setFile(value);
    }

    @And("^the user sets Description of file content to (.+)$")
    public void theUserSetsDescriptionOfFileContentToContent(String value) throws Throwable {
        analysisScriptAndSoftwarePage.setFileContent(value);
    }

    @And("^the user sets Research Registry ID to (.+)$")
    public void theUserSetsResearchRegistryIDToRegistryID(String value) throws Throwable {
        analysisScriptAndSoftwarePage.setRegistryID(value);
    }

    @And("^the user sets Transfer Request Justification to (.+)$")
    public void theUserSetsTransferRequestJustificationToJustification(String value) throws Throwable {
        analysisScriptAndSoftwarePage.setTransferRequestJustification(value);
    }

    @And("^the user sets Programming Language to (.+)$")
    public void theUserSetsProgrammingLanguageToLanguage(String value) throws Throwable{
        analysisScriptAndSoftwarePage.setProgrammingLanguage(value);
    }

    @And("^the user sets Transfer Agreement to (.+)$")
    public void theUserSetsTransferAgreementToAgreement(String value) throws Throwable{
        analysisScriptAndSoftwarePage.setTransfer(value);
    }

    @And("the user submits Form")
    public void theUserSubmitsForm() {
        analysisScriptAndSoftwarePage.submitButton();
    }

    @And("the user selects Contract Research Organisation form")
    public void theUserSelectsContractResearchOrganisationForm() {
        contractResearchOrganisationPage.setContractResearchOrganisationForms();
    }

    @And("^the user sets Contract UserGroup to (.+)$")
    public void theUserSetsContractUserGroupToUserGroup(String value) throws Throwable{
        contractResearchOrganisationPage.setUserGroupValue(value);
    }

    @And("^the user sets Contract Description of file content to (.+)$")
    public void theUserSetsContractDescriptionOfFileContentToContent (String value)throws Throwable{
        contractResearchOrganisationPage.setFileContent(value);
    }

    @And("^the user sets Contract Transfer Request Justification to (.+)$")
    public void theUserSetsContractTransferRequestJustificationToJustification(String value) throws Throwable {
        contractResearchOrganisationPage.setTransferRequestJustification(value);
    }

    @And("^the user sets Contract Transfer Agreement to (.+)$")
    public void theUserSetsContractTransferAgreementToAgreement(String value) throws Throwable {
        contractResearchOrganisationPage.setTransfer(value);
    }

    @And("^the user sets Contract PartcipantID to (.+)$")
    public void theUserSetsContractPartcipantIDToParticipantID(String value) throws Throwable {
        contractResearchOrganisationPage.setParticipantID(value);
    }

    @And("the user selects REFindings form")
    public void theUserSelectsREFindingsForm() {
        reFindingsPage.selectREFindingsForms();
    }

    @And("^the user sets REFindings UserGroup to (.+)$")
    public void theUserSetsREFindingsUserGroupToUserGroup(String value) {
        reFindingsPage.setUserGroupValue(value);
    }

    @And("^the user sets REFindings Description of file content to (.+)$")
    public void theUserSetsREFindingsDescriptionOfFileContentToContent(String value) {
        reFindingsPage.setFileContent(value);
    }

    @And("^the user sets REFindings Transfer Request Justification to (.+)$")
    public void theUserSetsREFindingsTransferRequestJustificationToJustification(String value) {
        reFindingsPage.setTransferRequestJustification(value);
    }

    @And("^the user sets REFindings Disease Type to (.+)$")
    public void theUserSetsREFindingsDiseaseTypeToDiseaseType(String value) {
        reFindingsPage.setDiseaseTypeValue(value);
    }

    @And("^the user sets REFindings Aim of Export to (.+)$")
    public void theUserSetsREFindingsAimOfExportToAim(String value) {
        reFindingsPage.setAimOfExport(value);
    }

    @And("^the user sets REFindings Transfer Agreement to (.+)$")
    public void theUserSetsREFindingsTransferAgreementToAgreement(String value) {
        reFindingsPage.setTransfer(value);
    }

    @And("the user selects Diagnostic Discovery form")
    public void theUserSelectsDiagnosticDiscoveryForm() {
        diagnosticDiscoveryPage.selectDiagnosticDiscoveryForms();
    }

    @And("^the user sets Diagnostic UserGroup to (.+)$")
    public void theUserSetsDiagnosticUserGroupToUserGroup(String value) {
        diagnosticDiscoveryPage.setUserGroupValue(value);
    }

    @And("^the user sets Diagnostic Research Registry ID to (.+)$")
    public void theUserSetsDiagnosticResearchRegistryIDToRegistryID(String value) {
        diagnosticDiscoveryPage.setRegistryID(value);
    }

    @And("^the user sets Diagnostic expertise to (.+)$")
    public void theUserSetsDiagnosticExpertiseToExpertise(String value) {
        diagnosticDiscoveryPage.setExpertise(value);
    }

    @And("^the user sets Diagnostic contact with clinical team to (.+)$")
    public void theUserSetsDiagnosticContactWithClinicalTeamToContact(String value) {
        diagnosticDiscoveryPage.setContactClinicalTeamDropDownValue(value);
    }

    @And("^user sets Diagnostic report potentially diagnostic variant to (.+)$")
    public void userSetsDiagnosticReportPotentiallyDiagnosticVariantToReport(String value) {
        diagnosticDiscoveryPage.setReportPotentialVariantDropDownValue(value);
    }

    @And("^user sets Diagnostic participant ids and variants to (.+)$")
    public void userSetsDiagnosticParticipantIdsAndVariantsToPartcipantIDsAndVariants(String value) {
        diagnosticDiscoveryPage.setParticipantIDandVariantsTextBox(value);
    }

    @And("^user sets Diagnostic Genome build to (.+)$")
    public void userSetsDiagnosticGenomeBuildToGenomeBuild(String value) {
        diagnosticDiscoveryPage.setGenomeBuildDropDownValue(value);
    }

    @And("the user selects GeneMatcher request form")
    public void theUserSelectsGeneMatcherRequestForm() {
        geneMatcherPage.selectGeneMatcherForms();
    }

    @And("^the user sets GeneMatcher UserGroup to (.+)$")
    public void theUserSetsGeneMatcherUserGroupToUserGroup(String value) {
        geneMatcherPage.setUserGroupValue(value);
    }

    @And("^the user sets GeneMatcher Gene Symbol to (.+)$")
    public void theUserSetsGeneMatcherGeneSymbolToGeneSymbol(String value) {
        geneMatcherPage.setGeneSymbol(value);
    }

    @And("^the user sets GeneMatcher Zygosity to (.+)$")
    public void theUserSetsGeneMatcherZygosityToZygosity(String value) {
        geneMatcherPage.setZygosityValue(value);
    }

    @And("^the user sets GeneMatcher Function to (.+)$")
    public void theUserSetsGeneMatcherFunctionToFunction(String value) {
        geneMatcherPage.setFunction(value);
    }

    @And("^the user sets GeneMatcher Inheritance model to (.+)$")
    public void theUserSetsGeneMatcherInheritanceModelToInheritanceModel(String value) {
        geneMatcherPage.setInheritanceModel(value);
    }

    @And("^the user sets GeneMatcher Phenotype term to (.+)$")
    public void theUserSetsGeneMatcherPhenotypeTermToPhenotypeTerm(String value) {
        geneMatcherPage.setPhenotypeTerm(value);
    }

    @And("^the user sets GeneMatcher PartcipantID to (.+)$")
    public void theUserSetsGeneMatcherPartcipantIDToParticipantID(String value) {
        geneMatcherPage.setFile(value);
    }

    @And("^the user sets GeneMatcher Transfer Agreement to (.+)$")
    public void theUserSetsGeneMatcherTransferAgreementToAgreement(String value) {
        geneMatcherPage.setTransfer(value);
    }
}//end
