package sjkj.wen.superduck.activity.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class StringUtil {
	/* ˵�����ƶ���134��135��136��137��138��139��150��151��157(TD)��158��159��187��188  
     * ��ͨ��130��131��132��152��155��156��185��186 
     * ���ţ�133��153��180��189  
     * �ܽ��������ǵ�һλ�ض�Ϊ1���ڶ�λ�ض�Ϊ3��5��8������λ�õĿ���Ϊ0-9  
     * ��֤���� �ֻ��� �̻����� 
     * 
     */  
    public static boolean isPhoneNumberValid(String phoneNumber) {  
    boolean isValid = false;  
 
    String expression = "((^(13|15|18)[0-9]{9}$)|(^0[1,2]{1}\\d{1}-?\\d{8}$)|(^0[3-9] {1}\\d{2}-?\\d{7,8}$)|(^0[1,2]{1}\\d{1}-?\\d{8}-(\\d{1,4})$)|(^0[3-9]{1}\\d{2}-? \\d{7,8}-(\\d{1,4})$))";  
    CharSequence inputStr = phoneNumber;  
 
    Pattern pattern = Pattern.compile(expression);  
 
    Matcher matcher = pattern.matcher(inputStr);  
 
    if (matcher.matches() ) {  
    isValid = true;  
    }  
 
    return isValid;  
 
    }  
}
