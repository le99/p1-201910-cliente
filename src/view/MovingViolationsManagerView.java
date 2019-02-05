package view;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

import controller.Controller;
import model.data_structures.IQueue;
import model.data_structures.IStack;
import model.vo.VODaylyStatistic;
import model.vo.VOMovingViolations;
import model.vo.VOViolationCode;

public class MovingViolationsManagerView 
{
	/**
	 * Constante con el número maximo de datos maximo que se deben imprimir en consola
	 */
	public static final int N = 20;
	
	public static void main(String[] args) 
	{
		Scanner sc = new Scanner(System.in);
		boolean fin=false;
		Controller controller = new Controller();
		
		while(!fin)
		{
			printMenu();
			
			int option = sc.nextInt();
			
			switch(option)
			{
				case 0:
					System.out.println("Ingrese el cuatrimestre (1, 2 o 3)");
					int numeroCuatrimestre = Integer.parseInt(sc.next());
					controller.loadMovingViolations(numeroCuatrimestre);
					break;
				
				case 1:
					boolean isUnique = controller.verifyObjectIDIsUnique();
					System.out.println("El objectId es único: " + isUnique);
					break;
					
				case 2:
					
					System.out.println("Ingrese la fecha con hora inicial (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaInicialReq2A = convertirFecha_Hora_LDT(sc.next());
					
					System.out.println("Ingrese la fecha con hora final (Ej : 28/03/2017T15:30:20)");
					LocalDateTime fechaFinalReq2A = convertirFecha_Hora_LDT(sc.next());
					
					IQueue<VOMovingViolations> resultados2 = controller.getMovingViolationsInRange(fechaInicialReq2A, fechaFinalReq2A);
					
					for(VOMovingViolations v: resultados2) {
						System.out.println("ObjectID: " + v.objectId() + ", issued: " + v.getTicketIssueDate());
					}
					
					break;
					
				case 3:
					
					System.out.println("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode3 = sc.next();
					
					double [] promedios3 = controller.avgFineAmountByViolationCode(violationCode3);
					
					System.out.println("FINEAMT promedio sin accidente: " + promedios3[0] + ", con accidente:" + promedios3[1]);
					break;
						
					
				case 4:
					
					System.out.println("Ingrese el ADDRESS_ID");
					String addressId4 = sc.next();

					System.out.println("Ingrese la fecha con hora inicial (Ej : 28/03/2017)");
					LocalDate fechaInicialReq4A = convertirFecha(sc.next());
					
					System.out.println("Ingrese la fecha con hora final (Ej : 28/03/2017)");
					LocalDate fechaFinalReq4A = convertirFecha(sc.next());
					
					IStack<VOMovingViolations> resultados4 = controller.getMovingViolationsAtAddressInRange(addressId4, fechaInicialReq4A, fechaFinalReq4A);
					
					System.out.println("OBJECTID\t TICKETISSUEDAT\t STREETSEGID\t ADDRESS_ID");

					for(VOMovingViolations v: resultados4) {
						System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getStreetSegId() + "\t" + v.getAddressId());
					}
					
					break;
					
				case 5:
					System.out.println("Ingrese el limite inferior de FINEAMT  (Ej: 50)");
					double limiteInf5 = Double.parseDouble(sc.next());
					
					System.out.println("Ingrese el limite superior de FINEAMT  (Ej: 50)");
					double limiteSup5 = Double.parseDouble(sc.next());
					
					IQueue<VOViolationCode> violationCodes = controller.violationCodesByFineAmt(limiteInf5, limiteSup5);
					
					System.out.println("VIOLATIONCODE\t FINEAMT promedio");

					for(VOViolationCode v: violationCodes) {
						System.out.println(v.getViolationCode() + "\t" + v.getAvgFineAmt());
					}
					break;
				
				case 6:
					
					System.out.println("Ingrese el limite inferior de TOTALPAID (Ej: 200)");
					double limiteInf6 = Double.parseDouble(sc.next());
					
					System.out.println("Ingrese el limite superior de TOTALPAID (Ej: 200)");
					double limiteSup6 = Double.parseDouble(sc.next());
					
					System.out.println("Ordenar Ascendentmente: (Ej: true)");
					boolean ascendente6 = Boolean.parseBoolean(sc.next());					
					
					IStack<VOMovingViolations> resultados6 = controller.getMovingViolationsByTotalPaid(limiteInf6, limiteSup6, ascendente6);
		
					System.out.println("OBJECTID\t TICKETISSUEDAT\t TOTALPAID");
					for(VOMovingViolations v: resultados6) {
						System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getTotalPaid());
					}
					
					break;
					
				case 7:
					
					System.out.println("Ingrese la hora inicial (Ej: 23)");
					int horaInicial7 = Integer.parseInt(sc.next());
					
					System.out.println("Ingrese la hora final (Ej: 23)");
					int horaFinal7 = Integer.parseInt(sc.next());
					
					
					IQueue<VOMovingViolations> resultados7 = controller.getMovingViolationsByHour(horaInicial7, horaFinal7);
		
					System.out.println("OBJECTID\t TICKETISSUEDAT\t VIOLATIONDESC");
					for(VOMovingViolations v: resultados7) {
						System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getViolationDescription());
					}
					
					break;
					
					
				case 8:
					
					System.out.println("Ingrese el VIOLATIONCODE (Ej : T210)");
					String violationCode8 = sc.next();
					
					double [] resultado8 = controller.avgAndStdDevFineAmtOfMovingViolation(violationCode8);
					
					System.out.println("FINEAMT promedio: " + resultado8[0] + ", desviación estandar:" + resultado8[1]);
					break;
					
				case 9:
					
					System.out.println("Ingrese la hora inicial (Ej: 23)");
					int horaInicial9 = Integer.parseInt(sc.next());
					
					System.out.println("Ingrese la hora final (Ej: 23)");
					int horaFinal9 = Integer.parseInt(sc.next());
					
					
					int resultado9 = controller.countMovingViolationsInHourRange(horaInicial9, horaFinal9);
					
					System.out.println("Número de infracciones: " + resultado9);
					break;
				
				case 10:	
					System.out.println("Porcentaje de infracciones que tuvieron accidentes por hora. 2018");
					System.out.println("Hora| % de accidentes");
					System.out.println("00 | X");
					System.out.println("01 | X");
					System.out.println("02 | XX");
					System.out.println("03 | XXXXX");
					System.out.println("04 | XXXXXXXX");
					System.out.println("05 | XXXXXXXXX");
					System.out.println("06 | XXXXXXXXX");
					System.out.println("07 | XXXXXXXXXX");
					System.out.println("08 | XXXXXXXXXXX");
					System.out.println("09 | XXXXXXXXXXXXX");
					System.out.println("10 | XXXXXXXXXXXXXX");
					System.out.println("11 | XXXXXXXXXXXXXX");
					System.out.println("12 | XXXXXXXXXXXXXXXX");
					System.out.println("13 | XXXX");
					System.out.println("14 | XXXXXX");
					System.out.println("15 | XXXXXXXXXXXXXXXX");
					System.out.println("16 | XXXXXXXXXXX");
					System.out.println("17 | XXXXXX");
					System.out.println("18 | XXXXXXXXXXXXXXXX");
					System.out.println("19 | XXXXXXXXXX");
					System.out.println("20 | XXX");
					System.out.println("21 | XXXXX");
					System.out.println("22 | XXXX");
					System.out.println("23 | XX");
					System.out.println("");
					System.out.println("Cada X representa Y%");



					break;
					
					
				case 11:
					
					System.out.println("Ingrese la fecha inicial (Ej : 28/03/2017)");
					LocalDate fechaInicial11 = convertirFecha(sc.next());
					
					System.out.println("Ingrese la fecha final (Ej : 28/03/2017)");
					LocalDate fechaFinal11 = convertirFecha(sc.next());
					
					double resultados11 = controller.totalDebt(fechaInicial11, fechaFinal11);
					
					System.out.println("Deuda total "+ resultados11);

					
					break;
				
				case 12:	
					System.out.println("Deuda acumulada por mes de infracciones. 2018");
					System.out.println("Mes| Dinero");
					System.out.println("01| X");
					System.out.println("02| XX");
					System.out.println("03 | XXXXXX");
					System.out.println("04 | XXXXXXXXXX");
					System.out.println("");
					System.out.println("Cada X representa $YYYY USD");
					
					break;
					
				case 13:	
					fin=true;
					sc.close();
					break;
			}
		}
	}

	private static void printMenu() {
		System.out.println("---------ISIS 1206 - Estructuras de datos----------");
		System.out.println("---------------------Taller 3----------------------");
		System.out.println("0. Cargar datos del cuatrimestre");
		System.out.println("1. Verificar que OBJECTID es en realidad un identificador único");
		System.out.println("2. Consultar infracciones por fecha/hora inicial y fecha/hora final");
		System.out.println("3. Dar FINEAMT promedio con y sin accidente por VIOLATIONCODE");
		System.out.println("4. Consultar infracciones por direccion entre fecha inicial y fecha final");

		
		System.out.println("5. Consultar los tipos de infracciones (VIOLATIONCODE) con su valor (FINEAMT) promedio en un rango dado");
		System.out.println("6. Consultar infracciones donde la cantidad pagada (TOTALPAID) esta en un rango dado. Se ordena por fecha de infracción");
		System.out.println("7. Consultar infracciones por hora inicial y hora final, ordenada ascendentemente por VIOLATIONDESC");
		System.out.println("8. Dado un tipo de infracción (VIOLATIONCODE) informar el (FINEAMT) promedio y su desviación estándar.");

		System.out.println("9. El número de infracciones que ocurrieron en un rango de horas del día. Se define el rango de horas por valores enteros en el rango [0, 24]");
		System.out.println("10. Grafica ASCII con el porcentaje de infracciones que tuvieron accidentes por hora del día");
		System.out.println("11. La deuda (TOTALPAID – FINEAMT - PENALTY1 – PENALTY2) total por infracciones que se dieron en un rango de fechas.");
		System.out.println("12. Grafica ASCII con la deuda acumulada total por infracciones");

		
		System.out.println("13. Salir");
		System.out.println("Digite el n�mero de opci�n para ejecutar la tarea, luego presione enter: (Ej., 1):");
		
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
