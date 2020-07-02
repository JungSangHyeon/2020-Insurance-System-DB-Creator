package main;

import component.AttributeInfo;
import component.AttributeInfo.PKAdditional;
import component.AttributeInfo.Type;
import component.MySQL;
import component.MySQL.FKAdditional;
import component.TableInfo;
import constant.EAccidentHistory;
import constant.EDisease;
import constant.EFamilyIllHistory;
import constant.EInsuranceType;
import constant.ETargetCustomer;

public class Main {
	
	// cd C:\Program Files\MySQL\MySQL Server 8.0\bin
	// mysql -u root -p

	public static void main(String[] args) {
		MySQL mySQL = new MySQL();
		mySQL.login("root", "sh199919");
		mySQL.useDB("ssinarack"); // DB는 미리 만들어 놓을 것
		
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Create Table@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		TableInfo SystemUserData = new TableInfo("SystemUserData");
		AttributeInfo systemUserDataID = new AttributeInfo("systemUserDataID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo loginID= new AttributeInfo("loginID", Type.Varchar(20));
		AttributeInfo loginPW = new AttributeInfo("loginPW", Type.Varchar(20));
		SystemUserData.addAttributeInfo(systemUserDataID, loginID, loginPW);
		mySQL.createTable(SystemUserData);
		
		TableInfo AbsEmployeeData = new TableInfo("AbsEmployeeData");
		AttributeInfo absEmployeeDataID = new AttributeInfo("absEmployeeDataID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo EmployeeName= new AttributeInfo("EmployeeName", Type.Varchar(20)); // 이름이 겹쳐서 어쩔 수 없이..
		AbsEmployeeData.addAttributeInfo(absEmployeeDataID, EmployeeName);
		mySQL.createTable(AbsEmployeeData);
		
		TableInfo AccidentData = new TableInfo("AccidentData");
		AttributeInfo accidentDataID = new AttributeInfo("accidentDataID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo date = new AttributeInfo("date", Type.Varchar(100)); // 날짜 아님
		AttributeInfo location = new AttributeInfo("location", Type.Varchar(200));
		AttributeInfo accidentType = new AttributeInfo("accidentType", Type.Varchar(200)); // 이름 겹침
		AccidentData.addAttributeInfo(accidentDataID, date, location, accidentType);
		mySQL.createTable(AccidentData);
		
		TableInfo AccidentInvestigation = new TableInfo("AccidentInvestigation");
		AttributeInfo accidentInvestigationID = new AttributeInfo("accidentInvestigationID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo scenario = new AttributeInfo("scenario", Type.Varchar(200));
		AttributeInfo damage = new AttributeInfo("damage", Type.Varchar(100));
		AttributeInfo treatment = new AttributeInfo("treatment", Type.Varchar(100));
		AttributeInfo treatmentCost = new AttributeInfo("treatmentCost", Type.Int());
		AccidentInvestigation.addAttributeInfo(accidentInvestigationID, scenario, damage, treatment, treatmentCost);
		mySQL.createTable(AccidentInvestigation);
		
		TableInfo Payjudge = new TableInfo("Payjudge");
		AttributeInfo payjudgeID = new AttributeInfo("payjudgeID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo payJudge = new AttributeInfo("payJudge", Type.Varchar(200));
		AttributeInfo evidence = new AttributeInfo("evidence", Type.Varchar(200));
		AttributeInfo relatedLaw = new AttributeInfo("relatedLaw", Type.Varchar(200));
		Payjudge.addAttributeInfo(payjudgeID, payJudge, evidence, relatedLaw);
		mySQL.createTable(Payjudge);
		
		TableInfo LossData = new TableInfo("LossData");
		AttributeInfo lossDataID = new AttributeInfo("lossDataID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo pay = new AttributeInfo("pay", Type.Int());
		AttributeInfo judgeEvidence = new AttributeInfo("judgeEvidence", Type.Varchar(200));
		LossData.addAttributeInfo(lossDataID, pay, judgeEvidence);
		mySQL.createTable(LossData);
		
		TableInfo Lawsuit = new TableInfo("Lawsuit");
		AttributeInfo lawsuitID = new AttributeInfo("lawsuitID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo payOut = new AttributeInfo("payOut", Type.Int()); // 위에 pay랑 겹쳐서 바꿨습니다~
		Lawsuit.addAttributeInfo(lawsuitID, payOut);
		mySQL.createTable(Lawsuit);
		
		TableInfo RewardData = new TableInfo("RewardData");
		AttributeInfo rewardDataID = new AttributeInfo("rewardDataID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo reAccidentDataID = new AttributeInfo("reAccidentDataID", Type.Int());
		AttributeInfo reAccidentInvestigationID = new AttributeInfo("reAccidentInvestigationID", Type.Int());
		AttributeInfo rePayjudgeID = new AttributeInfo("rePayjudgeID", Type.Int());
		AttributeInfo reLossDataID = new AttributeInfo("reLossDataID", Type.Int());
		AttributeInfo reLawsuitID = new AttributeInfo("reLawsuitID", Type.Int());
		RewardData.addAttributeInfo(rewardDataID, reAccidentDataID, reAccidentInvestigationID, rePayjudgeID, reLossDataID, reLawsuitID);
		mySQL.createTable(RewardData);
		
		TableInfo Customer = new TableInfo("Customer");
		AttributeInfo customerID = new AttributeInfo("customerID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo CustomerName = new AttributeInfo("CustomerName", Type.Varchar(20));
		AttributeInfo residence = new AttributeInfo("residence", Type.Varchar(100));
		AttributeInfo phoneNum = new AttributeInfo("phoneNum", Type.Varchar(15));
		AttributeInfo email = new AttributeInfo("email", Type.Varchar(30));
		AttributeInfo familyIllHistory = new AttributeInfo("familyIllHistory", EFamilyIllHistory.class);
		AttributeInfo socialSecurityNum = new AttributeInfo("socialSecurityNum", Type.Varchar(20));
		AttributeInfo gender = new AttributeInfo("gender", Type.Boolean());
		AttributeInfo job = new AttributeInfo("job", Type.Varchar(30));
		AttributeInfo property = new AttributeInfo("property", Type.Int());
		AttributeInfo accidentHistory = new AttributeInfo("accidentHistory", EAccidentHistory.class);
		AttributeInfo age = new AttributeInfo("age", Type.Int());
		AttributeInfo accountNum = new AttributeInfo("accountNum", Type.Varchar(30));	
		Customer.addAttributeInfo(customerID, CustomerName, residence, phoneNum, email, familyIllHistory, socialSecurityNum, gender, job, property, accidentHistory, age, accountNum);
		mySQL.createTable(Customer);
		
		TableInfo Insurance = new TableInfo("Insurance");
		AttributeInfo insuranceID = new AttributeInfo("insuranceID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo insuranceName = new AttributeInfo("insuranceName", Type.Varchar(30));
		AttributeInfo content = new AttributeInfo("content", Type.Varchar(100));
		AttributeInfo insuranceRatePermit = new AttributeInfo("insuranceRatePermit", Type.Boolean());
		AttributeInfo productPermit = new AttributeInfo("productPermit", Type.Boolean());
		AttributeInfo lossPercent = new AttributeInfo("lossPercent", Type.Decimal(16, 4));
		AttributeInfo insuranceType = new AttributeInfo("insuranceType", EInsuranceType.class);
		AttributeInfo insuranceRateInfo = new AttributeInfo("insuranceRateInfo", Type.Varchar(200));
		Insurance.addAttributeInfo(insuranceID, insuranceName, content, insuranceRatePermit, productPermit, lossPercent, insuranceType, insuranceRateInfo);
		mySQL.createTable(Insurance);
		
		TableInfo CustIns = new TableInfo("CustIns");
		AttributeInfo cusInsCustomerID = new AttributeInfo("cusInsCustomerID", Type.Int());
		AttributeInfo cusInsInsuranceID = new AttributeInfo("cusInsInsuranceID", Type.Int());
		CustIns.addAttributeInfo(cusInsCustomerID, cusInsInsuranceID);
		mySQL.createTable(CustIns);
		
		TableInfo CustomerTask = new TableInfo("CustomerTask");
		AttributeInfo cusTaskCustomerID = new AttributeInfo("cusTaskCustomerID", Type.Int());
		AttributeInfo cusTaskRewardDataID = new AttributeInfo("cusTaskRewardDataID", Type.Int());
		CustomerTask.addAttributeInfo(cusTaskCustomerID, cusTaskRewardDataID);
		mySQL.createTable(CustomerTask);
		
		TableInfo AccidentInvestigator = new TableInfo("AccidentInvestigator");
		AttributeInfo accidentInvestigatorID = new AttributeInfo("accidentInvestigatorID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo accidentInvestigatorState = new AttributeInfo("accidentInvestigatorState", Type.Boolean());
		AccidentInvestigator.addAttributeInfo(accidentInvestigatorID, accidentInvestigatorState);
		mySQL.createTable(AccidentInvestigator);
		
		TableInfo AccidentInvestigatorTask = new TableInfo("AccidentInvestigatorTask");
		AttributeInfo aiTaskAccidentInvestigatorID = new AttributeInfo("aiTaskAccidentInvestigatorID", Type.Int());
		AttributeInfo aiTaskRewardDataID = new AttributeInfo("aiTaskRewardDataID", Type.Int());
		AccidentInvestigatorTask.addAttributeInfo(aiTaskAccidentInvestigatorID, aiTaskRewardDataID);
		mySQL.createTable(AccidentInvestigatorTask);
		
		TableInfo LossCheckMan = new TableInfo("LossCheckMan");
		AttributeInfo lossCheckManID = new AttributeInfo("lossCheckManID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo lossCheckManState = new AttributeInfo("lossCheckManState", Type.Boolean());
		LossCheckMan.addAttributeInfo(lossCheckManID, lossCheckManState);
		mySQL.createTable(LossCheckMan);
		
		TableInfo LossCheckManTask = new TableInfo("LossCheckManTask");
		AttributeInfo lsTaskLossCheckManID = new AttributeInfo("lsTaskLossCheckManID", Type.Int());
		AttributeInfo lsTaskRewardDataID = new AttributeInfo("lsTaskRewardDataID", Type.Int());
		LossCheckManTask.addAttributeInfo(lsTaskLossCheckManID, lsTaskRewardDataID);
		mySQL.createTable(LossCheckManTask);
		
		TableInfo Payjudger = new TableInfo("Payjudger");
		AttributeInfo payjudgerID = new AttributeInfo("payjudgerID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo payjudgerState = new AttributeInfo("payjudgerState", Type.Boolean());
		Payjudger.addAttributeInfo(payjudgerID, payjudgerState);
		mySQL.createTable(Payjudger);
		
		TableInfo PayjudgerTask = new TableInfo("PayjudgerTask");
		AttributeInfo pjTaskPayjudgerID = new AttributeInfo("pjTaskPayjudgerID", Type.Int());
		AttributeInfo pjTaskRewardDataID = new AttributeInfo("pjTaskRewardDataID", Type.Int());
		PayjudgerTask.addAttributeInfo(pjTaskPayjudgerID, pjTaskRewardDataID);
		mySQL.createTable(PayjudgerTask);

		TableInfo Lawyer = new TableInfo("Lawyer");
		AttributeInfo lawyerID = new AttributeInfo("lawyerID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo lawyerState = new AttributeInfo("lawyerState", Type.Boolean());
		Lawyer.addAttributeInfo(lawyerID, lawyerState);
		mySQL.createTable(Lawyer);
		
		TableInfo LawyerTask = new TableInfo("LawyerTask");
		AttributeInfo lwTaskLawyerID = new AttributeInfo("lwTaskLawyerID", Type.Int());
		AttributeInfo lwTaskRewardDataID = new AttributeInfo("lwTaskRewardDataID", Type.Int());
		LawyerTask.addAttributeInfo(lwTaskLawyerID, lwTaskRewardDataID);
		mySQL.createTable(LawyerTask);
		
		TableInfo DiseaseIns = new TableInfo("DiseaseIns");
		AttributeInfo diseaseID = new AttributeInfo("diseaseID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo disease = new AttributeInfo("disease", EDisease.class);
		DiseaseIns.addAttributeInfo(diseaseID, disease);
		mySQL.createTable(DiseaseIns);
		
		TableInfo InsuranceRatePermitMan = new TableInfo("InsuranceRatePermitMan");
		AttributeInfo insuranceRatePermitManID = new AttributeInfo("insuranceRatePermitManID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo insuranceRatePermitManState = new AttributeInfo("insuranceRatePermitManState", Type.Boolean());
		InsuranceRatePermitMan.addAttributeInfo(insuranceRatePermitManID, insuranceRatePermitManState);
		mySQL.createTable(InsuranceRatePermitMan);
		
		TableInfo InsuranceRatePermitManTask = new TableInfo("InsuranceRatePermitManTask");
		AttributeInfo insTaskInsuranceRatePermitManID = new AttributeInfo("insTaskInsuranceRatePermitManID", Type.Int());
		AttributeInfo insTaskInsuranceID = new AttributeInfo("insTaskInsuranceID", Type.Int());
		InsuranceRatePermitManTask.addAttributeInfo(insTaskInsuranceRatePermitManID, insTaskInsuranceID);
		mySQL.createTable(InsuranceRatePermitManTask);	
		
		TableInfo ProductPermitMan = new TableInfo("ProductPermitMan");
		AttributeInfo productPermitManID = new AttributeInfo("productPermitManID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo productPermitManState = new AttributeInfo("productPermitManState", Type.Boolean());
		ProductPermitMan.addAttributeInfo(productPermitManID, productPermitManState);
		mySQL.createTable(ProductPermitMan);
		
		TableInfo ProductPermitManTask = new TableInfo("ProductPermitManTask");
		AttributeInfo proTaskProductPermitManID = new AttributeInfo("proTaskProductPermitManID", Type.Int());
		AttributeInfo proTaskInsuranceID = new AttributeInfo("proTaskInsuranceID", Type.Int());
		ProductPermitManTask.addAttributeInfo(proTaskProductPermitManID, proTaskInsuranceID);
		mySQL.createTable(ProductPermitManTask);	
		
		TableInfo Developer = new TableInfo("Developer");
		AttributeInfo developerID = new AttributeInfo("developerID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo developerState = new AttributeInfo("developerState", Type.Boolean());
		Developer.addAttributeInfo(developerID, developerState);
		mySQL.createTable(Developer);	
		
		TableInfo SalesMan = new TableInfo("SalesMan");
		AttributeInfo salesManID = new AttributeInfo("salesManID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo salesManState = new AttributeInfo("salesManState", Type.Boolean());
		SalesMan.addAttributeInfo(salesManID, salesManState);
		mySQL.createTable(SalesMan);	
		
		TableInfo SalesManager = new TableInfo("SalesManager");
		AttributeInfo salesManagerID = new AttributeInfo("salesManagerID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo salesManagerState = new AttributeInfo("salesManagerState", Type.Boolean());
		SalesManager.addAttributeInfo(salesManagerID, salesManagerState);
		mySQL.createTable(SalesManager);
		
		TableInfo ActivityPlan = new TableInfo("ActivityPlan");
		AttributeInfo activityPlanID = new AttributeInfo("activityPlanID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo activityPlanTitle = new AttributeInfo("title", Type.Varchar(100));  // 이름 겹쳐서 수정
		AttributeInfo salesDuration = new AttributeInfo("salesDuration", Type.Varchar(30));
		AttributeInfo salesGoal = new AttributeInfo("salesGoal", Type.Int());
		AttributeInfo activityGoal = new AttributeInfo("activityGoal", Type.Varchar(100));
		AttributeInfo additionalJobOffer = new AttributeInfo("additionalJobOffer", Type.Int());
		AttributeInfo salesTargetCustomer = new AttributeInfo("salesTargetCustomer",ETargetCustomer.class);
		ActivityPlan.addAttributeInfo(activityPlanID, activityPlanTitle, salesDuration, salesGoal, activityGoal, additionalJobOffer, salesTargetCustomer);
		mySQL.createTable(ActivityPlan);
		
		TableInfo SalesTrainingPlan = new TableInfo("SalesTrainingPlan");
		AttributeInfo salesTrainingPlanID = new AttributeInfo("salesTrainingPlanID", Type.Int(), PKAdditional.PrimaryKey());
		AttributeInfo salesPalnTitle = new AttributeInfo("title", Type.Varchar(100));  
		AttributeInfo place = new AttributeInfo("place", Type.Varchar(50));  // 이름 겹쳐서 수정
		AttributeInfo salesTraningPlanDate = new AttributeInfo("salesTraningPlanDate", Type.Varchar(30));
		AttributeInfo salesTrainingGoal = new AttributeInfo("salesTrainingGoal", Type.Varchar(100));
		AttributeInfo salesTrainingContent = new AttributeInfo("salesTrainingContent", Type.Varchar(200));
//		AttributeInfo target = new AttributeInfo("salesDuration", ETrainingTargetEmployee.class);
		SalesTrainingPlan.addAttributeInfo(salesTrainingPlanID, salesPalnTitle, place, 
				salesTraningPlanDate, salesTrainingGoal, salesTrainingContent);
		mySQL.createTable(SalesTrainingPlan);

		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Associate Table@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		// abs employee data
		mySQL.addForeignKey(AbsEmployeeData, absEmployeeDataID, SystemUserData, systemUserDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		// reward Data
		mySQL.addForeignKey(RewardData, reAccidentDataID , AccidentData, accidentDataID,FKAdditional.OnDeleteSetNull,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(RewardData, reAccidentInvestigationID , AccidentInvestigation, accidentInvestigationID,FKAdditional.OnDeleteSetNull,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(RewardData, rePayjudgeID , Payjudge, payjudgeID,FKAdditional.OnDeleteSetNull,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(RewardData, reLossDataID , LossData, lossDataID,FKAdditional.OnDeleteSetNull,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(RewardData, reLawsuitID , Lawsuit, lawsuitID,FKAdditional.OnDeleteSetNull,FKAdditional.OnUpdateCascade);
		
		// custIns
		mySQL.addForeignKey(CustIns, cusInsCustomerID , Customer, customerID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(CustIns, cusInsInsuranceID , Insurance, insuranceID, FKAdditional.OnDeleteNoAction,FKAdditional.OnUpdateCascade);
		
		// Insurance
		mySQL.addForeignKey(DiseaseIns, diseaseID , Insurance, insuranceID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);

		// customerTask
		mySQL.addForeignKey(CustomerTask, cusTaskCustomerID , Customer, customerID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(CustomerTask, cusTaskRewardDataID , RewardData, rewardDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);

		// accidentInvestigatorTask
		mySQL.addForeignKey(AccidentInvestigatorTask, aiTaskAccidentInvestigatorID , AccidentInvestigator, accidentInvestigatorID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(AccidentInvestigatorTask, aiTaskRewardDataID , RewardData, rewardDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);

		// LossCheckManTask
		mySQL.addForeignKey(LossCheckManTask, lsTaskLossCheckManID , LossCheckMan, lossCheckManID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(LossCheckManTask, lsTaskRewardDataID , RewardData, rewardDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		// PayjudgerTask
		mySQL.addForeignKey(PayjudgerTask, pjTaskPayjudgerID , Payjudger, payjudgerID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(PayjudgerTask, pjTaskRewardDataID , RewardData, rewardDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		// LawyerTask
		mySQL.addForeignKey(LawyerTask, lwTaskLawyerID , Lawyer, lawyerID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(LawyerTask, lwTaskRewardDataID , RewardData, rewardDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);

		// InsuranceRatePermitManTask
		mySQL.addForeignKey(InsuranceRatePermitManTask, insTaskInsuranceRatePermitManID , InsuranceRatePermitMan, insuranceRatePermitManID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(InsuranceRatePermitManTask, insTaskInsuranceID , Insurance, insuranceID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		// ProductPermitManTask
		mySQL.addForeignKey(ProductPermitManTask, proTaskProductPermitManID , ProductPermitMan, productPermitManID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		mySQL.addForeignKey(ProductPermitManTask, proTaskInsuranceID , Insurance, insuranceID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);

		// accidentInvestigator
		mySQL.addForeignKey(AccidentInvestigator, accidentInvestigatorID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//LossCheckMan
		mySQL.addForeignKey(LossCheckMan, lossCheckManID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//Payjudger
		mySQL.addForeignKey(Payjudger, payjudgerID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//Lawyer
		mySQL.addForeignKey(Lawyer, lawyerID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//InsuranceRatePermitMan
		mySQL.addForeignKey(InsuranceRatePermitMan, insuranceRatePermitManID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//ProductPermitMan
		mySQL.addForeignKey(ProductPermitMan, productPermitManID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
			
		//Developer
		mySQL.addForeignKey(Developer, developerID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//SalesMan
		mySQL.addForeignKey(SalesMan, salesManID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		//SalesManager
		mySQL.addForeignKey(SalesManager, salesManagerID , AbsEmployeeData, absEmployeeDataID,FKAdditional.OnDeleteCascade,FKAdditional.OnUpdateCascade);
		
		// @@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@Save Instructions@@@@@@@@@@@@@@@@@@@@@@@@@@@@@@
		
		mySQL.saveHistoryAsFile();
		System.out.println("Finish");
	}
}