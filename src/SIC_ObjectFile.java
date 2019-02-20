/**
 *
 *  author  :   Chia Yuan Lin (林家源)
 *
 *  email   :   lo919201@gmail.com
 *
 *  ObjectFile 輸出判斷
 *
 * **/
public class SIC_ObjectFile
{
    private String T_Code_Start;
    private int T_Code_lengh;
    private String T_Code;

    public SIC_ObjectFile()
    {
        T_Code_Start="";
        T_Code_lengh=0;
        T_Code="";
    }

    public String H_Ccard(String start_name,String start,String end)
    {
        String result="H";

        result = result + start_name;                       //加名字
        for(int k=0; k< 6 - start_name.length() ; k++ )
            result = result+ " ";

        for(int k=0; k< 6 - start.length() ; k++ )
            result = result+ "0";
        result = result + Integer.toHexString(Integer.valueOf(start)).toUpperCase() ;        //加起始位置




        String Code_lenght = Integer.toHexString( Integer.valueOf(end) -Integer.valueOf(start) ).toUpperCase();

        for(int k=0; k< 6 - Code_lenght.length() ; k++ )
            result = result+ "0";
        result = result + Code_lenght ;        //加起始位置

        return result;
    }

    /*                            設定 T_Code_Start                                       */
    public void setT_Code_Start(String Code_Start)
    {
        T_Code_Start =Code_Start.toUpperCase();
    }

    /*                            設定 T_Code                                  */
    public void setT_Code(String Code)
    {
        T_Code =T_Code+Code.toUpperCase();
        T_Code_lengh = T_Code.length()/2 ;
    }
    /*                            設定 get_T_Code_lengh                                  */
    public int getT_Code_lengh()
    {
        return  T_Code_lengh;
    }



    public String toString()
    {
        String result_Start="";
        for(int k=0; k< 6 - T_Code_Start.length() ; k++ )
            result_Start = result_Start+"0";
        result_Start = result_Start + T_Code_Start;

        String result_lengh="";
        if(T_Code_lengh<16)
            result_lengh="0"+Integer.toHexString(T_Code_lengh);
        else
            result_lengh=Integer.toHexString(T_Code_lengh);

        return ("T" +  result_Start +  result_lengh + T_Code);
    }


    public String E_Ccard(String start)
    {
        String result="E";
        for(int k=0; k< 6 - start.length() ; k++ )
            result = result+ "0";
        result = result + Integer.toHexString(Integer.valueOf(start)).toUpperCase() ;        //加起始位置

        return  result;
    }

}
