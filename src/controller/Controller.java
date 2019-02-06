package controller;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;
import view.MovingViolationsManagerView;

public class Controller {

	private MovingViolationsManagerView view;

	public Controller() {
		view = new MovingViolationsManagerView();
	}
	
	public void run() {
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		
		while(!fin)
		{
			view.printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 0:
					view.printMessage("Ingrese el cuatrimestre (1, 2 o 3)");
					int numeroCuatrimestre = sc.nextInt();
					controller.loadMovingViolations(numeroCuatrimestre);
					break;
				
				case 1:
					boolean isUnique = controller.verifyObjectIDIsUnique();
					view.printMessage("El objectId es único: " + isUnique);
					break;
					
				case 2:
					
					view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaInicialReq2A = convertirFecha_Hora_LDT(sc.next());
					
					view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaFinalReq2A = convertirFecha_Hora_LDT(sc.next());
					
					IQueue<VOMovingViolations> resultados2 = controller.getMovingViolationsInRange(fechaInicialReq2A, fechaFinalReq2A);
					
					view.printMovingViolationsReq2(resultados2);
					
					break;
					
				case 3:
					
					view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode3 = sc.next();
					
					double [] promedios3 = controller.avgFineAmountByViolationCode(violationCode3);
					
					view.printMessage("FINEAMT promedio sin accidente: " + promedios3[0] + ", con accidente:" + promedios3[1]);
					break;
						
					
				case 4:
					
					view.printMessage("Ingrese el ADDRESS_ID");
					String addressId4 = sc.next();

					view.printMessage("Ingrese la fecha con hora inicial (Ej : 28/03/2017)");
					LocalDate fechaInicialReq4A = convertirFecha(sc.next());
					
					view.printMessage("Ingrese la fecha con hora final (Ej : 28/03/2017)");
					LocalDate fechaFinalReq4A = convertirFecha(sc.next());
					
					IStack<VOMovingViolations> resultados4 = controller.getMovingViolationsAtAddressInRange(addressId4, fechaInicialReq4A, fechaFinalReq4A);
					
					view.printMovingViolationsReq4(resultados4);
					
					break;
					
				case 5:
					view.printMessage("Ingrese el limite inferior de FINEAMT  (Ej: 50)");
					double limiteInf5 = sc.nextDouble();
					
					view.printMessage("Ingrese el limite superior de FINEAMT  (Ej: 50)");
					double limiteSup5 = sc.nextDouble();
					
					IQueue<VOViolationCode> violationCodes = controller.violationCodesByFineAmt(limiteInf5, limiteSup5);
					view.printViolationCodesReq5(violationCodes);
					break;
				
				case 6:
					
					view.printMessage("Ingrese el limite inferior de TOTALPAID (Ej: 200)");
					double limiteInf6 = sc.nextDouble();
					
					view.printMessage("Ingrese el limite superior de TOTALPAID (Ej: 200)");
					double limiteSup6 = sc.nextDouble();
					
					view.printMessage("Ordenar Ascendentmente: (Ej: true)");
					boolean ascendente6 = sc.nextBoolean();				
					
					IStack<VOMovingViolations> resultados6 = controller.getMovingViolationsByTotalPaid(limiteInf6, limiteSup6, ascendente6);
					view.printMovingViolationReq6(resultados6);
					break;
					
				case 7:
					
					view.printMessage("Ingrese la hora inicial (Ej: 23)");
					int horaInicial7 = sc.nextInt();
					
					view.printMessage("Ingrese la hora final (Ej: 23)");
					int horaFinal7 = sc.nextInt();
					
					IQueue<VOMovingViolations> resultados7 = controller.getMovingViolationsByHour(horaInicial7, horaFinal7);
					view.printMovingViolationsReq7(resultados7);
					break;
					
				case 8:
					
					view.printMessage("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode8 = sc.next();
					
					double [] resultado8 = controller.avgAndStdDevFineAmtOfMovingViolation(violationCode8);
					
					view.printMessage("FINEAMT promedio: " + resultado8[0] + ", desviación estandar:" + resultado8[1]);
					break;
					
				case 9:
					
					view.printMessage("Ingrese la hora inicial (Ej: 23)");
					int horaInicial9 = sc.nextInt();
					
					view.printMessage("Ingrese la hora final (Ej: 23)");
					int horaFinal9 = sc.nextInt();
					
					int resultado9 = controller.countMovingViolationsInHourRange(horaInicial9, horaFinal9);
					
					view.printMessage("Número de infracciones: " + resultado9);
					break;
				
				case 10:
					view.printMovingViolationsByHourReq10();
					break;
					
				case 11:
					view.printMessage("Ingrese la fecha inicial (Ej : 28/03/2017)");
					LocalDate fechaInicial11 = convertirFecha(sc.next());
					
					view.printMessage("Ingrese la fecha final (Ej : 28/03/2017)");
					LocalDate fechaFinal11 = convertirFecha(sc.next());
					
					double resultados11 = controller.totalDebt(fechaInicial11, fechaFinal11);
					view.printMessage("Deuda total "+ resultados11);
					break;
				
				case 12:	
					view.printTotalDebtbyMonthReq12();
					
					break;
					
				case 13:	
					fin=true;
					sc.close();
					break;
			}
		}

	}
	
	
	public void loadMovingViolations(int numeroCuatrimestre) {
		
	}
	
	public IQueue <VODaylyStatistic> getDailyStatistics () {
		return null;
	}
	
	public IStack <VOMovingViolations> nLastAccidents(int n) {
		return null;
	}

	public boolean verifyObjectIDIsUnique() {
		return false;
	}

	public IQueue<VOMovingViolations> getMovingViolationsInRange(LocalDateTime fechaInicial,
			LocalDateTime fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] avgFineAmountByViolationCode(String violationCode3) {
		return new double [] {0.0 , 0.0};
	}

	public IStack<VOMovingViolations> getMovingViolationsAtAddressInRange(String addressId,
			LocalDate fechaInicial, LocalDate fechaFinal) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOViolationCode> violationCodesByFineAmt(double limiteInf5, double limiteSup5) {
		// TODO Auto-generated method stub
		return null;
	}

	public IStack<VOMovingViolations> getMovingViolationsByTotalPaid(double limiteInf6, double limiteSup6,
			boolean ascendente6) {
		// TODO Auto-generated method stub
		return null;
	}

	public IQueue<VOMovingViolations> getMovingViolationsByHour(int horaInicial7, int horaFinal7) {
		// TODO Auto-generated method stub
		return null;
	}

	public double[] avgAndStdDevFineAmtOfMovingViolation(String violationCode8) {
		// TODO Auto-generated method stub
		return new double [] {0.0 , 0.0};
	}

	public int countMovingViolationsInHourRange(int horaInicial9, int horaFinal9) {
		// TODO Auto-generated method stub
		return 0;
	}

	public double totalDebt(LocalDate fechaInicial11, LocalDate fechaFinal11) {
		// TODO Auto-generated method stub
		return 0;
	}
	
	
	/**
	 * Convertir fecha a un objeto LocalDate
	 * @param fecha fecha en formato dd/mm/aaaa con dd para dia, mm para mes y aaaa para agno
	 * @return objeto LD con fecha
	 */
	private static LocalDate convertirFecha(String fecha)
	{
		return LocalDate.parse(fecha, DateTimeFormatter.ofPattern("dd/MM/yyyy"));
	}

	
	/**
	 * Convertir fecha y hora a un objeto LocalDateTime
	 * @param fecha fecha en formato dd/mm/aaaaTHH:mm:ss con dd para dia, mm para mes y aaaa para agno, HH para hora, mm para minutos y ss para segundos
	 * @return objeto LDT con fecha y hora integrados
	 */
	private static LocalDateTime convertirFecha_Hora_LDT(String fechaHora)
	{
		return LocalDateTime.parse(fechaHora, DateTimeFormatter.ofPattern("dd/MM/yyyy'T'HH:mm:ss"));
	}
}
