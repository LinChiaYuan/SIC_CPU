/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * 用於判斷是否為指令碼
 *
 * */
public class SIC_Instruct
{
    /*     63個指令碼       */
    String[] sic_Instruct={"ADD" ,"ADDF","ADDR"  ,"AND"   ,"CLEAR","COMP","COMPF","COMPR","DIV" ,"DIVF",
            "DIVR","FIX" ,"FLOAT" ,"HIO"   ,"J"    ,"JEQ" ,"JGT"  ,"JLT"  ,"JSUB","LDA" ,"LDB","LDCH",
            "LDF" ,"LDL" ,"LDS"   ,"LDT"   ,"LDX"  ,"LPS" ,"UML"  ,"MULF" ,"MULR","NORM","OR" ,"RD",
            "RMO" ,"RSUB","SHIFTL","SHIFTR","SIO"  ,"SSK" ,"STA"  ,"STB"  ,"STCH","STF" ,"STI",
            "STL" ,"STS" ,"STSW"  ,"STT"   ,"STX"  ,"SUB" ,"SUBF" ,"SUBR" ,"SVC" ,"TD"  ,"TIO",
            "TIX" ,"TIXR","WD"    ,"WORD"  ,"RESW" ,"RESB","END"  ,"BYTE" };


    public boolean instruct_equal(String str)
    {
        boolean result=false;
        for(int i=0;i<64;i++)
        {
            if(sic_Instruct[i].equals(str.toUpperCase()))    //判斷是否為指令碼
            {
                result=true;
                break;
            }
        }
        return result;
    }

    public boolean instruct_Rsub_equal(String str)
    {
        boolean result=false;

        if(str.toUpperCase().equals("RSUB"))              //判斷是否為RSUB
        {
            result=true;
        }
        return result;
    }



}
