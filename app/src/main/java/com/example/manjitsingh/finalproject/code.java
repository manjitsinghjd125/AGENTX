package com.example.manjitsingh.finalproject;

/**
 * Created by manjitsingh on 2017-12-13.
 */

public class code {
    public String encrypt(String data){
        String edate="";
        int datelength = data.length();
        for(int y=0;y<datelength;y++){
            char a = data.charAt(y);
            switch(a){
                case '1':

                    edate=edate.concat("A");
                    break;
                case '2':

                    edate=edate.concat("C");
                    break;
                case '3':

                    edate=edate.concat("E");
                    break;
                case '4':

                    edate=edate.concat("G");
                    break;
                case '5':

                    edate=edate.concat("I");
                    break;
                case '6':

                    edate=edate.concat("K");
                    break;
                case '7':

                    edate=edate.concat("M");
                    break;
                case '8':

                    edate=edate.concat("O");
                    break;
                case '9':

                    edate=edate.concat("Q");
                    break;
                case '0':

                    edate=edate.concat("S");
                    break;
                default:
                    switch(a){
                        case 'a':
                        case 'A':
                            edate=edate.concat("2");
                            break;
                        case 'b':
                        case 'B':
                            edate=edate.concat("3");
                            break;
                        case 'c':
                        case 'C':
                            edate=edate.concat("5");
                            break;
                        case 'd':
                        case 'D':
                            edate=edate.concat("7");
                            break;
                        case 'e':
                        case 'E':
                            edate=edate.concat("11");
                            break;
                        case 'f':
                        case 'F':
                            edate=edate.concat("13");
                            break;
                        case 'g':
                        case 'G':
                            edate=edate.concat("17");
                            break;
                        case 'h':
                        case 'H':
                            edate=edate.concat("19");
                            break;
                        case 'i':
                        case 'I':
                            edate=edate.concat("23");
                            break;
                        case 'j':
                        case 'J':
                            edate=edate.concat("29");
                            break;
                        case 'k':
                        case 'K':
                            edate=edate.concat("31");
                            break;
                        case 'l':
                        case 'L':
                            edate=edate.concat("37");
                            break;
                        case 'm':
                        case 'M':
                            edate=edate.concat("41");
                            break;
                        case 'n':
                        case 'N':
                            edate=edate.concat("43");
                            break;
                        case 'o':
                        case 'O':
                            edate=edate.concat("47");
                            break;
                        case 'p':
                        case 'P':
                            edate=edate.concat("53");
                            break;
                        case 'q':
                        case 'Q':
                            edate=edate.concat("59");
                            break;
                        case 'r':
                        case 'R':
                            edate=edate.concat("61");
                            break;
                        case 's':
                        case 'S':
                            edate=edate.concat("67");
                            break;
                        case 't':
                        case 'T':
                            edate=edate.concat("71");
                            break;
                        case 'u':
                        case 'U':
                            edate=edate.concat("73");
                            break;
                        case 'v':
                        case 'V':
                            edate=edate.concat("79");
                            break;
                        case 'w':
                        case 'W':
                            edate=edate.concat("83");
                            break;
                        case 'x':
                        case 'X':
                            edate=edate.concat("89");
                            break;
                        case 'y':
                        case 'Y':
                            edate=edate.concat("97");
                            break;
                        case 'z':
                        case 'Z':
                            edate=edate.concat("101");
                            break;
                        case ':':
                            edate=edate.concat("@");
                            break;
                        case ' ':

                            edate=edate.concat("#");
                            break;
                        case '.':

                            edate=edate.concat("$");
                            break;

                        default:
                            System.out.print("Do not have key for this");
                    }
                    if(y>=0&&y<datelength){
                        edate=edate.concat("/");
                    }
            }}
        return edate;
    }
    public String decode(String data){
        String decode1="",code1="",result="";
        int datelength = data.length();
        for(int i = 0 ;i<datelength;i++){
            char a = data.charAt(i);
            if(a == '/'){
                // System.out.println(code);
                switch(code1){
                    case "@gent":
                        decode1=decode1.concat("Agent");
                        code1 = "";
                        break;
                    case "2":
                        decode1=decode1.concat("A");
                        code1="";

                        break;
                    case "3":
                        decode1=decode1.concat("B");
                        code1="";

                        break;
                    case "5":
                        decode1=decode1.concat("C");
                        code1="";

                        break;
                    case "7":
                        decode1=decode1.concat("D");

                        code1="";
                        break;
                    case "11":
                        decode1=decode1.concat("E");

                        code1="";
                        break;
                    case "13":
                        decode1=decode1.concat("F");

                        code1="";
                        break;
                    case "17":
                        decode1=decode1.concat("G");
                        code1="";

                        break;
                    case "19":
                        decode1=decode1.concat("H");
                        code1="";

                        break;
                    case "23":
                        decode1=decode1.concat("I");
                        code1="";

                        break;
                    case "29":
                        decode1=decode1.concat("J");

                        code1="";
                        break;
                    case "31":
                        decode1=decode1.concat("K");

                        code1="";
                        break;
                    case "37":
                        decode1=decode1.concat("L");

                        code1="";
                        break;
                    case "41":
                        decode1=decode1.concat("M");

                        code1="";
                        break;
                    case "43":
                        decode1=decode1.concat("N");
                        code1="";

                        break;
                    case "47":
                        decode1=decode1.concat("O");

                        code1="";
                        break;
                    case "53":
                        decode1=decode1.concat("P");

                        code1="";
                        break;
                    case "59":
                        decode1=decode1.concat("Q");
                        code1="";

                        break;
                    case "61":
                        decode1=decode1.concat("R");
                        code1="";

                        break;
                    case "67":
                        decode1=decode1.concat("S");
                        code1="";

                        break;
                    case "71":
                        decode1=decode1.concat("T");

                        code1="";
                        break;
                    case "73":
                        decode1=decode1.concat("U");
                        code1="";

                        break;
                    case "79":
                        decode1=decode1.concat("V");
                        code1="";

                        break;
                    case "83":
                        decode1=decode1.concat("W");
                        code1="";

                        break;
                    case "89":
                        decode1=decode1.concat("X");
                        code1="";

                        break;
                    case "97":
                        decode1=decode1.concat("Y");
                        code1="";

                        break;
                    case "101":
                        decode1=decode1.concat("Z");
                        code1="";

                        break;
                    case "@":

                        decode1=decode1.concat(":");
                        code1="";

                        break;
                    case "#":

                        decode1=decode1.concat(" ");
                        code1="";

                        break;
                    case "$":

                        decode1=decode1.concat(".");
                        code1="";

                        break;
                    default:
                        System.out.print("Do not have key for this");
                }
            }
            else{
                switch(a){
                    case 'A':
                        decode1=decode1.concat("1");
                        code1="";
                        break;
                    case 'C':
                        decode1=decode1.concat("2");
                        code1="";
                        break;

                    case 'E':
                        decode1=decode1.concat("3");
                        code1="";
                        break;

                    case 'G':
                        decode1=decode1.concat("4");
                        code1="";
                        break;

                    case 'I':
                        decode1=decode1.concat("5");
                        code1="";
                        break;

                    case 'K':
                        decode1=decode1.concat("6");
                        code1="";
                        break;

                    case 'M':
                        decode1=decode1.concat("7");
                        code1="";
                        break;

                    case 'O':
                        decode1=decode1.concat("8");
                        code1="";
                        break;

                    case 'Q':
                        decode1=decode1.concat("9");
                        code1="";
                        break;

                    case 'S':
                        decode1=decode1.concat("0");
                        code1="";
                        break;


                    default:
                        code1 += a;
                }

            }
        }
        result=decode1.toLowerCase();
        return result;

    }
}
