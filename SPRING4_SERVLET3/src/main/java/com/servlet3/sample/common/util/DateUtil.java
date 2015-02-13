package com.servlet3.sample.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import org.springframework.util.StringUtils;

public class DateUtil {

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 일단위의 값을 Return하여 준다.
	*/
	public static final char DAY = 'D';

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 시단위의 값을 Return하여 준다.
	*/
	public static final char HOUR = 'H';

	/**
	* 일자들끼리 연산을 수행할때 Return값의 단위를 정해주는 상수로 사용된다.
	* 이 상수는 분단위의 값을 Return하여 준다.
	*/
	public static final char MIN = 'M';
	
	public static int getHour( ) {
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		return cal.get(Calendar.HOUR_OF_DAY) ;
	}
	public static int getMinute( ) {
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		return cal.get(Calendar.MINUTE) ;
	}
	public static int getYear( ) {
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		return cal.get(Calendar.YEAR) ;
	}
	public static int getMonth( ) {
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		return cal.get(Calendar.MONTH) ;
	}
	public static int getDate( ) {
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		return cal.get(Calendar.DATE) ;
	}
	/**
	* 오늘 일자를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getToday("yyyy/MM/dd hh:mm a")<BR>
	* 결 과 ) 2001/12/07 10:10 오후<BR><BR>
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pOutformat String
	*/
	public static String getToday( String pOutformat) {
		String rDateString = null;
		Date vDate = new Date();

		try
		{
			rDateString = getDateFormat( pOutformat, vDate);

		} catch( Exception e ) {}

		return rDateString;
	}
	
	/**
	* 전달받은 날짜(Date)를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getToday("yyyy/MM/dd hh:mm a")<BR>
	* 결 과 ) 2001/12/07 10:10 오후<BR><BR>
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pOutformat String
	*/
	public static String getDateFormat( String pOutformat, Date vDate) {

		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);

		String rDateString = null;

		try
		{
			rDateString = pOutformatter.format(vDate);

		} catch( Exception e ) {}

		return rDateString;
	}
	/**
	* 전달받은 날짜(Date)를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getToday("yyyy/MM/dd hh:mm a")<BR>
	* 결 과 ) 2001/12/07 10:10 오후<BR><BR>
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pOutformat String
	*/
	public static String getDateFormat(String pIndate, String pInformat, String pOutformat ) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		
		String rDateString = "";
		Date vIndate = null;

		try {
			vIndate = pInformatter.parse(pIndate);
			rDateString = getDateFormat(pOutformat,vIndate ) ;

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	/**
	* 전달받은 날짜(Date)를 지정된 Format의 날짜 표현형식으로 돌려준다. <BR><BR>
	*
	* 사용예) getDateFormat("20101121","-")<BR>
	* 결 과 ) 2010-11-21<BR><BR>
	*
	*
	* @return java.lang.String
	* @param type String
	*/
	public static String getDateFormat( String date, String type) {
		String rDateString = null;
		rDateString = date.substring(0,4) + type + date.substring(4,6)+ type + date.substring(6) ;
		return rDateString;
	}
	/**
	* 입력받은 날짜에 일/시/분 단위의 값을 더하여 출력Format에 따라 값을 넘겨준다. <BR><BR>
	* Parameter는 입력일, 입력일 Format, 출력일 Format, 일단위 더하기, 시단위 더하기,
	* 분단위 더하기이다.
	*
 	* 간단한 사용예는 다음과 같다.
	*
	* 사용예) LLog.debug.println( getFormattedDateAdd("200201010605","yyyyMMddhhmm","yyyy/MM/dd HH:mm",-100,10,-11) );
	* 결과) 2001/09/23 15:54
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pIndate String
	* @param pInformat String
	* @param pOutformat String
	* @param pDay int
	* @param pHour int
	* @param pMin int
	*/
	public static String getFormattedDateAdd(String pIndate, String pInformat, String pOutformat, int pDay, int pHour, int pMin ) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);

		String rDateString = "";
		Date vIndate = null;
		long vAddon = ( pDay * 24L*60L*60L*1000L ) + ( pHour * 60L*60L*1000L ) + ( pMin * 60L*1000L );

		try
		{
			vIndate = pInformatter.parse(pIndate);

			Date vAddday = new Date( vIndate.getTime() + vAddon );
			
			
			
			rDateString = pOutformatter.format(vAddday);

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	/**
	* 입력받은 날짜에 월 단위의 값을 더하여 출력Format에 따라 값을 넘겨준다. <BR><BR>
	* Parameter는 입력일, 입력일 Format, 출력일 Format, 일단위 더하기, 시단위 더하기,
	* 분단위 더하기이다.
	*
 	* 간단한 사용예는 다음과 같다.
	*
	* 사용예) LLog.debug.println( getFormattedDateAdd("200201010605","yyyyMMddhhmm","yyyy/MM/dd HH:mm",-6) );
	* 결과) 2001/09/23 15:54
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pIndate String
	* @param pInformat String
	* @param pOutformat String
	* @param pDay int
	* @param pHour int
	* @param pMin int
	*/
	public static String getFormattedDateYearAdd(String pIndate, String pInformat, String pOutformat, int pYear) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		String rDateString = "";
		Date vIndate = null;

		try {
			vIndate = pInformatter.parse(pIndate);
			cal.setTime(vIndate) ;
			cal.add(Calendar.YEAR, pYear) ;
			rDateString = pOutformatter.format(cal.getTime());

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	/**
	* 입력받은 날짜에 월 단위의 값을 더하여 출력Format에 따라 값을 넘겨준다. <BR><BR>
	* Parameter는 입력일, 입력일 Format, 출력일 Format, 일단위 더하기, 시단위 더하기,
	* 분단위 더하기이다.
	*
 	* 간단한 사용예는 다음과 같다.
	*
	* 사용예) LLog.debug.println( getFormattedDateAdd("200201010605","yyyyMMddhhmm","yyyy/MM/dd HH:mm",-6) );
	* 결과) 2001/09/23 15:54
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pIndate String
	* @param pInformat String
	* @param pOutformat String
	* @param pDay int
	* @param pHour int
	* @param pMin int
	*/
	public static String getFormattedDateMonthAdd(String pIndate, String pInformat, String pOutformat, int pMonth) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		String rDateString = "";
		Date vIndate = null;

		try {
			vIndate = pInformatter.parse(pIndate);
			cal.setTime(vIndate) ;
			cal.add(Calendar.MONTH, pMonth) ;
			rDateString = pOutformatter.format(cal.getTime());

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	
	/**
	* 입력받은 날짜에 월 단위의 값을 더하여 출력Format에 따라 값을 넘겨준다. <BR><BR>
	* Parameter는 입력일, 입력일 Format, 출력일 Format, 일단위 더하기, 시단위 더하기,
	* 분단위 더하기이다.
	*
 	* 간단한 사용예는 다음과 같다.
	*
	* 사용예) LLog.debug.println( getFormattedDateAdd("200201010605","yyyyMMddhhmm","yyyy/MM/dd HH:mm",-6) );
	* 결과) 2001/09/23 15:54
	*
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	*
	* @return java.lang.String
	* @param pIndate String
	* @param pInformat String
	* @param pOutformat String
	* @param pDay int
	* @param pHour int
	* @param pMin int
	*/
	public static String getFormattedDateHourAdd(String pIndate, String pInformat, String pOutformat, int pHour) {

		SimpleDateFormat pInformatter =  new SimpleDateFormat (pInformat, java.util.Locale.KOREA);
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		String rDateString = "";
		Date vIndate = null;

		try {
			vIndate = pInformatter.parse(pIndate);
			cal.setTime(vIndate) ;
			cal.add(Calendar.HOUR, pHour) ;
			rDateString = pOutformatter.format(cal.getTime());

		} catch( Exception e ) {
			rDateString = pIndate;
		}

		return rDateString;
	}
	
	public static String getFormattedDateMonthAdd(Date date,String pOutformat, int pMonth) {
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		String rDateString = "";
		try {
			cal.setTime(date) ;
			cal.add(Calendar.MONTH, pMonth) ;
			rDateString = pOutformatter.format(cal.getTime());

		} catch( Exception e ) {
			rDateString = date.toString();
		}
		return rDateString;
	}
	public static boolean isDate( String pOutformat, String vDate) {
		SimpleDateFormat pOutformatter =  new SimpleDateFormat (pOutformat, java.util.Locale.KOREA);
		Calendar cal = Calendar.getInstance(Locale.getDefault()) ;
		try {
			pOutformatter.parse(vDate);
			return true ;
		} catch( Exception e ) {
			return false ;
		}
	}
	/**
	* 일자들의 계산을 수행한다..
	* 제일 마지막의 Parameter pType에 따라서 Return값이 다르다.
	* 둘째 Parameter는 첫째 Parameter의 입력 형식을 지정하고 넷째 Parameter는
	* 셋째 Parameter의 입력형식을 지정한다.
	* Return값의 단위를 정해주는 pType에는 4가지가 올 수 있는데
	* ECOMJDateU.DAY, ECOMJDateU.HOUR, ECOMJDateU.MIN, ECOMJDateU.SEC 이다.
	* 각각의 단위는 일단위, 시단위, 분단위 이다.
	* 첫째 Parameter로 입력받은 일자에서 셋째 Parameter로 입력받은 일자를 빼서
	* 나온 결과를 돌려준다.
	* Format은 J2SE의 SimpleDateFormat의 Documentation을 참고한다.
	* 간단한 사용예는 다음과 같다.
	*
	* LLog.debug.println(getComputedDate("2002/01/04 00:01","yyyy/MM/dd hh:mm","2002/01/02 23:59","yyyy/MM/dd hh:mm",ECOMJDateU.DAY));
	*
	* 작업 결과로 '1'이 표시된다.
	*
	* @return long
	* @param pIndate1 java.lang.String
	* @param pInformat1 java.lang.String
	* @param pIndate2 java.lang.String
	* @param pInformat2 java.lang.String
	* @param pType char
	*/
	
	public static long getComputedDate( String pIndate1, String pInformat1, String pIndate2, String pInformat2) {

		SimpleDateFormat pInformatter1 =  new SimpleDateFormat (pInformat1);
		SimpleDateFormat pInformatter2 =  new SimpleDateFormat (pInformat2);
		long vDategap = 0;
		try {
			Date vIndate1 = pInformatter1.parse(pIndate1);
			Date vIndate2 = pInformatter2.parse(pIndate2);
			vDategap = vIndate1.getTime() - vIndate2.getTime();
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
	
	public static long getComputedDate( String pIndate1, String pInformat1, String pIndate2, String pInformat2, String pType) {

		SimpleDateFormat pInformatter1 =  new SimpleDateFormat (pInformat1);
		SimpleDateFormat pInformatter2 =  new SimpleDateFormat (pInformat2);
		long vDategap = 0;
		try {
			Date vIndate1 = pInformatter1.parse(pIndate1);
			Date vIndate2 = pInformatter2.parse(pIndate2);
			vDategap = vIndate1.getTime() - vIndate2.getTime();
			
			if(pType.equals("S")){
				vDategap = vDategap /(1000);  			//초
			}else if(pType.equals("M")){
				vDategap = vDategap /(60*1000);  		//분
			}else if(pType.equals("H")){
				vDategap = vDategap /(60*60*1000);  	//시간
			}else if(pType.equals("D")){
				vDategap = vDategap /(24*60*60*1000);	//일
			}
		
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
	
	public static long getComputedDate( String pIndate, String pInformat , String pType) {

		SimpleDateFormat pInformatter1 =  new SimpleDateFormat (pInformat);
		Date curDate = null ;
		Calendar calendar = Calendar.getInstance() ;
		curDate = calendar.getTime() ;
		long vDategap = 0;
		try {
			Date vIndate1 = pInformatter1.parse(pIndate);
			vDategap = vIndate1.getTime() - curDate.getTime();
			
			if(pType.equals("S")){
				vDategap = vDategap /(1000);  			//초
			}else if(pType.equals("M")){
				vDategap = vDategap /(60*1000);  		//분
			}else if(pType.equals("H")){
				vDategap = vDategap /(60*60*1000);  	//시간
			}else if(pType.equals("D")){
				vDategap = vDategap /(24*60*60*1000);	//일
			}
		
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
	/**
	 * amount 차이만큼 날짜를 반환한다. 
	 * @param amount
	 * @return
	 */
	public static String getAddDate( int amount ) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        		 cal.add(Calendar.DATE, amount);
        StringBuilder buf = new StringBuilder();
        buf.append(Integer.toString(cal.get(1)));
        String month = Integer.toString(cal.get(2) + 1);
        if(month.length() == 1)
            month = "0" + month;
        String day = Integer.toString(cal.get(5));
        if(day.length() == 1)
            day = "0" + day;
        buf.append(month);
        buf.append(day);
        return buf.toString();
	}

	/**
	 * amount 차이만큼 날짜를 반환한다.
	 * @param basicDate 
	 * @param amount
	 * @return
	 */
	public static String getAddMonth( String basicDate , int amount ) {
	
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        int basicYear = Integer.parseInt(basicDate.substring(0,4));
        int basicMonth= Integer.parseInt(basicDate.substring(4,6))-1; 
        int basicDay  = Integer.parseInt(basicDate.substring(6,8));
        
        cal.set(basicYear, basicMonth, basicDay) ;
        cal.add(Calendar.MONTH, amount);
        
        StringBuilder buf = new StringBuilder();
        buf.append(Integer.toString(cal.get(1)));
        String month = Integer.toString(cal.get(2) + 1);
        if(month.length() == 1)
            month = "0" + month;
        String day = Integer.toString(cal.get(5));
        if(day.length() == 1)
            day = "0" + day;
        buf.append(month);
        buf.append(day);
        return buf.toString();
	}
	
	/**
	 * amount 차이만큼 날짜를 반환한다. 
	 * @param amount
	 * @return
	 */
	public static String getAddHour(int amount) {
		Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.add(Calendar.HOUR, amount);
        
		SimpleDateFormat dateprint = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		
		return dateprint.format(cal.getTime());
	}
	
	/**
	 * amount 차이만큼 날짜를 반환한다. 
	 * @param amount
	 * @return
	 */
	public static String getAddMonth( int amount ) {
        Calendar cal = Calendar.getInstance(Locale.getDefault());
        		 cal.add(Calendar.MONTH, amount);
        		 
        StringBuilder buf = new StringBuilder();
        buf.append(Integer.toString(cal.get(1)));
        String month = Integer.toString(cal.get(2) + 1);
        if(month.length() == 1)
            month = "0" + month;
        String day = Integer.toString(cal.get(5));
        if(day.length() == 1)
            day = "0" + day;
        buf.append(month);
        buf.append(day);
        return buf.toString();
	}
	
	public static int getMaxDay(int argYear, int argMonth){
		int[] cDate = {29, 31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31} ;
		int lastday = cDate[ argMonth-0 ] ;
		if( argMonth == 2 && ((argYear%4==0 && argYear%100!=0) || (argYear%400==0)) )
		lastday = cDate[0];

		return lastday;
	}
	
	/**
	 * 해당날짜가 유효한지 확인  
	 * @param date
	 * @return
	 */
	public static boolean isDate(String date) {
		boolean result = false ;
		if( !StringUtils.hasLength(date) ) {
			return false;
		}
		if( date.length() != 8  ) {
			return false ;
		}
		try {
			int year = Integer.parseInt(date.substring(0,4) ) ;
			int month = Integer.parseInt(date.substring(4,6) ) ;
			int day = Integer.parseInt(date.substring(6) ) ;
			result = isDate( year, month, day);
		}catch( Exception e ) {
			return false ;
		}
			
		return result ;
	}
	/**
	 * 해당날짜가 유효한지 확인 
	 * @param year
	 * @param month
	 * @param day
	 * @return
	 */
	public static boolean isDate(int year, int month, int day) {
		boolean result = false ;
		int lastDay = 0 ;
		if( year < 0 || month < 0 || day < 0 ) return false ;
		if ( month > 12 || day > 31 ) return false ;
		
		String year2 = ""+ year ;
		String month2 = "00" + month ;
		
		String yearMonth = year2+month2.substring(month2.length()-2) ;
		lastDay = getLastDayOfMonth(yearMonth);
		if(day > lastDay ) result = false ;
		else result = true ;
		return result ;
	}
		
	/**
	 * 해당월에 마지막 일자 구하기
	 * yyyymm
	 * @param yearMonth
	 * @return
	 */
	public static int getLastDayOfMonth( String yearMonth)  {
		Calendar cal = Calendar.getInstance() ;
		int year = Integer.parseInt( yearMonth.substring(0,4) ) ;
		int month = Integer.parseInt( yearMonth.substring(4,6) ) ;
		
		cal.set(year, month-1,1) ;
		
		return cal.getActualMaximum(Calendar.DAY_OF_MONTH) ;
	}
	
	/**
	 * <pre>
	 * 입력받은 날짜 시간 yyyy-MM-dd HH:mm:ss 형식으로 반한
	 * date는 format과 날짜형식이 같아야 한다.
	 * </pre>
	 * @param date EX)2011-11-11 11:11:11
	 * @param format EX) "yyyy-MM-dd HH:mm:ss"
	 * @return "yyyy-MM-dd HH:mm:ss"
	 * @throws ParseException
	 */
	public static String getReqDate(String date, String format) {
		return getCurrentDate(date, format, "yyyy-MM-dd HH:mm:ss");
		
	}	
	
	/**
	 * "yyyy-MM-dd HH:mm:SS"형의 날짜를 반환한다.
	@return yyyy-MM-dd HH:mm:SS 형의 날짜		
	*/
	public static String getCurrentDate() {
		return getCurrentDate("yyyy-MM-dd HH:mm:ss");
	}

	public static String getCurrentDate(String s) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s, java.util.Locale.KOREA);
		return simpledateformat.format(new Date());
	}
	
	public static String getCurrentDate(String date, String format, String s) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s, java.util.Locale.KOREA);
		SimpleDateFormat simpledateformat2 = new SimpleDateFormat(format, java.util.Locale.KOREA);
		
		try{
			return simpledateformat.format(simpledateformat2.parse(date));
		}catch (ParseException e) {
			return "";
		}
	}
	
	public static String getCurrentDate(Date date, String s) {
		SimpleDateFormat simpledateformat = new SimpleDateFormat(s, java.util.Locale.KOREA);
		return simpledateformat.format(date);
	}	
	
	public static int dayOfWeek(String date) {
		Calendar cal = Calendar.getInstance();
		int year = Integer.parseInt(date.substring(0,4) ) ;
		int month = Integer.parseInt(date.substring(4,6) ) ;
		int day = Integer.parseInt(date.substring(6) ) ;
		
		cal.set( year, month-1, day );
		return cal.get( Calendar.DAY_OF_WEEK );
	}
	
	
	public static long getDateDiff(String dataPart, String pIndate1) {

		String pInformat1 = "yyyy-MM-dd HH:mm:ss";
		String pInformat2 = "yyyy-MM-dd HH:mm:ss";
		
		return getDateDiff(dataPart, pIndate1, pInformat1, getCurrentDate(), pInformat2);
	}
	
	
	public static long getDateDiff(String dataPart, String pIndate1, String pIndate2) {

		String pInformat1 = "yyyy-MM-dd HH:mm:ss";
		String pInformat2 = "yyyy-MM-dd HH:mm:ss";
		
		return getDateDiff(dataPart, pIndate1, pInformat1, pIndate2, pInformat2);
	}
	/**<pre>	 * 
	 * getDataDiff : 년,월,일,시,분,초 로 지난 시간을 (long)로 리턴
	 * dataPart(y : 년, m : 월, d : 일, h : 시, n : 분, s : 초)
	 * pIndate1 EX)2011-01-01 01:01:01
	 * pInformat1 EX)yyyyMMdd hh:mm:ss
	 * pIndate2 EX)2011-12-31 01:01:01
	 * pInformat2 EX)yyyyMMdd hh:mm:ss
	 * pIndate1, pIndate2 값이 "now" 경우 현재 날짜가 들어간다.
	 * </pre>
	 * @param dataPart
	 * @param pIndate1
	 * @param pInformat1
	 * @param pIndate2
	 * @param pInformat2
	 * @return
	 */
	public static long getDateDiff(String dataPart, String pIndate1, String pInformat1, String pIndate2, String pInformat2) {

		Date now = Calendar.getInstance().getTime();
		SimpleDateFormat nowFormatter =  new SimpleDateFormat ("yyyyMMdd HH:mm:ss", java.util.Locale.KOREA);
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();
		
		SimpleDateFormat pInformatter1 =  new SimpleDateFormat (pInformat1, java.util.Locale.KOREA);
		SimpleDateFormat pInformatter2 =  new SimpleDateFormat (pInformat2, java.util.Locale.KOREA);
		
		pIndate1 = pIndate1.equals("now") ? nowFormatter.format(now).toString() : pIndate1;
		pIndate2 = pIndate2.equals("now") ? nowFormatter.format(now).toString() : pIndate2;
		
		pInformat1 = pIndate1.equals("now") ? "yyyyMMdd HH:mm:ss" : pInformat1;
		pInformat2 = pIndate2.equals("now") ? "yyyyMMdd HH:mm:ss" : pInformat2;
		
		long vDategap = 0;
		
		try {
			Date vIndate1 = pInformatter1.parse(pIndate1);
			Date vIndate2 = pInformatter2.parse(pIndate2);
			
			calendar1.setTime(vIndate1);
			calendar2.setTime(vIndate2);
			
			long vDateGapYear 	= calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
			long vDateGapMonth 	= calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
			
			if ("y".equals(dataPart))				
				vDategap = vDateGapYear;
			else if ("m".equals(dataPart))
				vDategap = (vDateGapYear * 12) + vDateGapMonth;
			else if ("d".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*3600*24));
			else if ("h".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*3600));
			else if ("n".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*60));
			else if ("s".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/1000);
			else
				vDategap = 0;
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
	
	/**<pre>	 * 
	 * getDataDiff : 년,월,일,시,분,초 로 지난 시간을 (long)로 리턴
	 * dataPart(y : 년, m : 월, d : 일, h : 시, n : 분, s : 초)
	 * </pre>
	 * @param dataPart
	 * @param pIndate1
	 * @param pIndate2
	 * @return
	 */
	public static long getDateDiff(String dataPart, Date pIndate1, Date pIndate2) {
		
		Calendar calendar1 = Calendar.getInstance();
		Calendar calendar2 = Calendar.getInstance();		
		
		long vDategap = 0;
		
		try {
			calendar1.setTime(pIndate1);
			calendar2.setTime(pIndate2);
			
			long vDateGapYear = calendar2.get(Calendar.YEAR) - calendar1.get(Calendar.YEAR);
			long vDateGapMonth = calendar2.get(Calendar.MONTH) - calendar1.get(Calendar.MONTH);
			
			if ("y".equals(dataPart))				
				vDategap = vDateGapYear;
			else if ("m".equals(dataPart))
				vDategap = (vDateGapYear * 12) + vDateGapMonth;
			else if ("d".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*3600*24));
			else if ("h".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*3600));
			else if ("n".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/(1000*60));
			else if ("s".equals(dataPart))
				vDategap = (long)((calendar2.getTimeInMillis() - calendar1.getTimeInMillis())/1000);
			else
				vDategap = 0;
		} catch ( Exception e ) { 
			e.printStackTrace();
			return 0;
		}

		return vDategap;
	}
	
	// 해당 년의 몇번째 주인지
	 public static int getYearWeek(String thisday) {
	 
	  int year = 0;
	  int month = 0;
	  int day = 0;
	  
	  Calendar cDate = Calendar.getInstance();  // Calendar 클래스의 인스턴스 생성
	  year = Integer.parseInt(getDateFormat(thisday, "yyyyMMdd", "yyyy" ));
	  month = Integer.parseInt(getDateFormat(thisday, "yyyyMMdd", "MM" ));
	  day = Integer.parseInt(getDateFormat(thisday, "yyyyMMdd", "dd" ));

	  cDate.set(year, month-1, day);
	  
	  return cDate.get(Calendar.WEEK_OF_YEAR);
	 }

	 
	  public static long diffOfDate(String begin, String end) throws Exception
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	 
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);
	 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (24 * 60 * 60 * 1000);
	 
	    return diffDays;
	  }

	 
	  public static long diffOfHour(String begin, String end) throws Exception
	  {
	    SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
	 
	    Date beginDate = formatter.parse(begin);
	    Date endDate = formatter.parse(end);
	 
	    long diff = endDate.getTime() - beginDate.getTime();
	    long diffDays = diff / (60 * 60 * 1000);
	 
	    return diffDays;
	  }
	  


	 /**
	 * <PRE>
	 * 1. MethodName	:	firstDateOfWeek
	 * 2. Comment		:	해당일의 첫번째 날 리턴
	 * 3. 작성자			:	retriver
	 * 4. 작성일			:	2012. 4. 20.	오후 5:33:31
	 * </PRE>
	 *
	 *	@param dtPrevDate
	 *	@return
	 *	@throws Exception
	 */
	public static String firstDateOfWeek(String  dtPrevDate) throws Exception
	{		    
        Calendar cal = Calendar.getInstance(Locale.getDefault());
		cal.setFirstDayOfWeek(Calendar.MONDAY); 
        cal.set(Integer.parseInt(dtPrevDate.substring(0, 4)), Integer.parseInt(dtPrevDate.substring(4, 6)) - 1, Integer.parseInt(dtPrevDate.substring(6)));
        
        int today = cal.get( Calendar.DAY_OF_WEEK);
        int firstDay = cal.getActualMinimum(Calendar.DAY_OF_WEEK);
		cal.add(Calendar.DAY_OF_MONTH, firstDay - today);
		  
        String month = Integer.toString(cal.get(Calendar.MONTH ) + 1);
        if(month.length() == 1)
            month = "0" + month;
        String day = Integer.toString(cal.get(Calendar.DAY_OF_MONTH ));
        if(day.length() == 1)
            day = "0" + day;

        StringBuilder buf = new StringBuilder();
        buf.append(Integer.toString(cal.get(Calendar.YEAR )));
        buf.append(month);
        buf.append(day);
        return buf.toString();
  }

 
}
