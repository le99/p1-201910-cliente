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
	
	public void printMenu() {
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
	
	public void printMessage(String mensaje) {
		System.out.println(mensaje);
	}
	
	public void printMovingViolationsReq2(IQueue<VOMovingViolations> resultados2) {
		for(VOMovingViolations v: resultados2) {
			System.out.println("ObjectID: " + v.objectId() + ", issued: " + v.getTicketIssueDate());
		}
	}
	
	public void printMovingViolationsReq4(IStack<VOMovingViolations> resultados4) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t STREETSEGID\t ADDRESS_ID");

		for(VOMovingViolations v: resultados4) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getStreetSegId() + "\t" + v.getAddressId());
		}
	}
	
	public void printViolationCodesReq5(IQueue<VOViolationCode> violationCodes) {
		System.out.println("VIOLATIONCODE\t FINEAMT promedio");

		for(VOViolationCode v: violationCodes) {
			System.out.println(v.getViolationCode() + "\t" + v.getAvgFineAmt());
		}
	}
	
	public void printMovingViolationReq6(IStack<VOMovingViolations> resultados6) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t TOTALPAID");
		for(VOMovingViolations v: resultados6) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getTotalPaid());
		}
	}
	
	public void printMovingViolationsReq7(IQueue<VOMovingViolations> resultados7) {
		System.out.println("OBJECTID\t TICKETISSUEDAT\t VIOLATIONDESC");
		for(VOMovingViolations v: resultados7) {
			System.out.println( v.objectId() + "\t" + v.getTicketIssueDate() + "\t" + v.getViolationDescription());
		}
	}
	
	public void printMovingViolationsByHourReq10() {
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
	}
	
	public void printTotalDebtbyMonthReq12() {
		System.out.println("Deuda acumulada por mes de infracciones. 2018");
		System.out.println("Mes| Dinero");
		System.out.println("01| X");
		System.out.println("02| XX");
		System.out.println("03 | XXXXXX");
		System.out.println("04 | XXXXXXXXXX");
		System.out.println("");
		System.out.println("Cada X representa $YYYY USD");
	}
	
}
