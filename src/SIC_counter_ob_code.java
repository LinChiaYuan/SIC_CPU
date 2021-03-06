/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * 計算ob-code
 *
 * */
public class SIC_counter_ob_code
{
    String[] sic_Instruct={"ADD" ,"ADDF","ADDR"  ,"AND"   ,"CLEAR","COMP","COMPF","COMPR","DIV" ,"DIVF",
            "DIVR","FIX" ,"FLOAT" ,"HIO"   ,"J"    ,"JEQ" ,"JGT"  ,"JLT"  ,"JSUB","LDA" ,"LDB","LDCH",
            "LDF" ,"LDL" ,"LDS"   ,"LDT"   ,"LDX"  ,"LPS" ,"UML"  ,"MULF" ,"MULR","NORM","OR" ,"RD",
            "RMO" ,"RSUB","SHIFTL","SHIFTR","SIO"  ,"SSK" ,"STA"  ,"STB"  ,"STCH","STF" ,"STI",
            "STL" ,"STS" ,"STSW"  ,"STT"   ,"STX"  ,"SUB" ,"SUBF" ,"SUBR" ,"SVC" ,"TD"  ,"TIO",
            "TIX" ,"TIXR","WD"    ,"WORD"  ,"RESW" ,"RESB","END"  ,"BYTE" };

    String[] ob={"18","58","90","40","B4"  ,"28","88","A0","24","64"  ,"9C","C4","C0","F4","3C",
            "30","34","38","48","00"  ,"68","50","70","08","6C"  ,"74","04","E0","20","60",
            "98","C8","44","D8","AC"  ,"4C","A4","A8","F0","EC"  ,"0C","78","54","80","D4",
            "14","7C","E8","84","10"  ,"1C","5C","94","B0","E0"  ,"F8","2C","B8","DC"};


    public String ob_code_add(String str,String instruct,String use)
    {
        int total=0;
        int count=use.length();
        String result="      ";
        for(int i=0;i<64;i++)
        {
            if(sic_Instruct[i].equals(instruct.toUpperCase()))   //尋找sic_Instruct位置
            {
                total=i;
                break;
            }
        }

        if(total<59)
        {
            result="";                     //先設初值
            if(total==35)
            {
                result=ob[total]+"0000";                //為RSUB
            }                                           //判端 X
            else if(( use.substring(0,2).equals("X,") )|| (use.substring(count-2,count).equals(",X") ))
            {
                result = ob[total]+Integer.toHexString(Integer.valueOf(str)+32768);
            }
            else
            {
                result = ob[total] ;            //依16進位大小 補0
                for(int k=0; k< 4 - ( Integer.toHexString(Integer.valueOf(str)).length()) ; k++ )
                    result = result + "0";
                result= result + Integer.toHexString(Integer.valueOf(str));
            }
        }
        else
        {
            if(total == 59)
            {
                result= Integer.toHexString(Integer.valueOf(use));
                for(int k=0; k< 6 - ( Integer.toHexString(Integer.valueOf(use)).length()) ; k++ )
                    result = "0"+ result ;	     //依16進位大小 補0
            }
            else if(total == 63)
            {
                result="";
                if(use.charAt(0) == 'X')
                    result = use.substring(2,count-1);
                else if(use.charAt(0) == 'C')
                {
                    for(int k=2;k<count-1;k++)
                    {

                        int Ascii = (int)use.charAt(k);      //轉Ascii
                        result = result + Integer.toHexString(Ascii).toUpperCase();
                    }
                }
            }
        }
        return result ;
    }

}
