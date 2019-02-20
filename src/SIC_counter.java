/**
 *
 * author  :   Chia Yuan Lin (林家源)
 *
 * email   :   lo919201@gmail.com
 *
 * */
public class SIC_counter
{
    String[] sic_Instruct={"ADD" ,"ADDF","ADDR"  ,"AND"   ,"CLEAR","COMP","COMPF","COMPR","DIV" ,"DIVF",
            "DIVR","FIX" ,"FLOAT" ,"HIO"   ,"J"    ,"FEQ" ,"JGT"  ,"JLT"  ,"JSUB","LDA" ,"LDB","LDCH",
            "LDF" ,"LDL" ,"LDS"   ,"LDT"   ,"LDX"  ,"LPS" ,"UML"  ,"MULF" ,"MULR","NORM","OR" ,"RD",
            "RMO" ,"RSUB","SHIFTL","SHIFTR","SIO"  ,"SSK" ,"STA"  ,"STB"  ,"STCH","STF" ,"STI",
            "STL" ,"STS" ,"STSW"  ,"STT"   ,"STX"  ,"SUB" ,"SUBF" ,"SUBR" ,"SVC" ,"TD"  ,"TIO",
            "TIX" ,"TIXR","WD"    ,"WORD"  ,"RESW" ,"RESB","END"  ,"BYTE" };

    int Add[]={ 3,3,2,3,2, 3,3,2,1,1, 1,3,3,3,3, 3,3,3,3,3, 3,3,3,3,3, 3,3,3,3,3,
            2,1,3,3,2, 3,2,2,1,3, 3,3,3,3,3, 3,3,3,3,3, 3,3,2,2,3, 1,
            3,2,3};


    public int address_add(String adr,String instruct,String use)
    {
        int intValue =  Integer.valueOf(adr);
        int total=0;
        int count=use.length();
        for(int i=0;i<64;i++)
        {
            if(sic_Instruct[i].equals(instruct.toUpperCase()))    // ´M§äsic_Instruct¦ì¸m
            {
                total=i;
                break;
            }
        }


        if(total<59)
        {
            intValue = intValue + Add[total];
        }
        else
        {
            if(total == 59)
                intValue = intValue + 3;
            else if(total == 60)
                intValue = intValue + Integer.valueOf(use)*3;
            else if(total == 61)
                intValue = intValue + Integer.valueOf(use);
            else if(total == 63)
            {
                if(use.charAt(0) == 'X')
                    intValue = intValue + (count-3)/2;
                else if(use.charAt(0) == 'C')
                    intValue = intValue + (count-3);
            }
        }

        return intValue;
    }





}
