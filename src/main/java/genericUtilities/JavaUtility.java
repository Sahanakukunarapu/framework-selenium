package genericUtilities;

import java.text.SimpleDateFormat;
import java.util.Date;
/**
 * this class contains reusable methods to perform java related operations 
 * @author vanik
 */
public class JavaUtility {
/**
 * This method fetches current date and time in specific format
 * @return
 */
	public String getCurrentTime() {
		Date date=new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("dd_MM_yy_hh_mm_ss");
		return sdf.format(date);
	}
}
