//MAKE SURE LINE 75ish IS FIXED
//When adding new things, change backslash to "~" and var name to "_"
//Spaces are still -, figure out why this doesn't work later
//Hearts are #

import java.util.*;
import java.io.*;

class CodeNameGenerator{
    final static String BOUND = "~left(~sqrt{~frac{-~left|y-6~right|}{y-6}}~sqrt{~frac{~left|y-2~right|}{y-2}}~right)";
    
    public static void main(String args[]) throws IOException{
        Map<String, String> wordList = new TreeMap<String, String>();
        System.out.println("Welcome to CodeNameGenerator");
        System.out.println();
        Scanner console = new Scanner(System.in);
        while(true){
            int option = doWhat(console);
            if(option == 1){
                System.out.print("What word would you like graphified? ");
                String response = console.next();
                System.out.println(response);
                System.out.println();
                wordList.put(response, makeNew(response) );
            } else if(option == 2){ 
                System.out.println();
                System.out.println("Here are your words:");
                for(String word : wordList.keySet() ){
                    System.out.println(word + " is...");
                    System.out.println(wordList.get(word) );
                    System.out.println();
                }
            } else if(option == 3){ 
                fillNewFile(console);
            } else { //Should be 4
                while(true){
                    fillNewFile(console);
                }
            }
        }
    }
    
    public static void fillNewFile(Scanner console) throws IOException{
        System.out.print("Making a new output file with the following name: " );
        File file = new File(console.next() );
        PrintStream output = new PrintStream(file);
        System.out.println("Please enter all the names you want to print, separated by ',' and no ' ':");
        String[] names = console.next().split(",");
        for(String name : names){
            output.println();
            output.println(name);
            output.println(makeNew(name, false) ); //Prints out first with no spacing
            output.println(makeNew(name, true) ); //Then with spacing
        }
    }
    
    //Returns true if user wants to make new word
    public static int doWhat(Scanner console){
        System.out.println("Would you like to make a new word (1), see word list (2), ");
        System.out.print("      or print to file (3) or print to multiple new files (4)? ");
        while(true){
            String response = console.next().trim();
            if(response.equals("1") ){
                return 1;
            } else if (response.equals("2") ){
                return 2;
            } else if (response.equals("3") ){
                return 3;
            } else if (response.equals("4") ){
                return 4;
            } else {
                System.out.print("Please enter an integer between ");
            }
        }
    }
    
    public static String makeNew(String word){
        return makeNew(word, false);
    }
    
    //Returns the graphified parameter String, note backslash is replaced with ~, location constants replaced with _
    public static String makeNew(String word, boolean spacing){
        StringBuilder s = new StringBuilder(BOUND);
        double location = 0.0;
        for(char ch : word.toUpperCase().toCharArray() ){
            for(Letter letter : Letter.values() ){
                if(ch == letter.getCharacter() ){
                    if(spacing){
                        s.append("\\\\"); //With the spacing
                    }
                    String runningEquation = "";
                    String[] parts = letter.getEquation().split("_"); //The "_" is where the location goes
                    for(int i = 0; i < parts.length; i++){
                        runningEquation += parts[i];
                        if(i < parts.length - 1){
                            runningEquation += (location); //Without +a 
                            //runningEquation += (location + "+a"); //With +a
                        }
                    }
                    s.append(runningEquation); 
                    location += letter.getDistance();
                    break;
                }
            }
        }
        System.out.println("The equation for " + word + " is...");
        String newString = s.append("=0").toString().replace('~','\\'); //MAKE SURE THIS IS FIXED BEFORE ANY USE
        System.out.println(newString);
        System.out.println();
        return newString;
    }
    
    //Contains all the letters, their code
    enum Letter{
        A('A', "~left(-~left(~frac{10}{11}~left(x-~frac{10}{3}-_~right)~right)^{4}-~left(y-4~right)~right)~left(y-6+3~left|x-~frac{10}{3}-_~right|~right)", 2.9),
        B('B', "~left(x-~frac{201}{100}-_~right)~left(2~left|~sin~left(~frac{~pi}{2}y-~pi~right)~right|-x+2+_~right)", 2.2),
        C('C', "~left(~left(~frac{11}{14}~left(y-4~right)~right)^{2}+2-~left(x-_~right)~right)", 2.9),
        D('D', "~left(-~left(~frac{9}{14}~left(y-4~right)~right)^{2}+~frac{71}{20}-~frac{18}{20}~left(x-_~right)~right)~left(x-~frac{201}{100}-_~right)", 2.2),
        E('E', "~left(x-1.99-_~right)~left(~left|~left(~sin~left(1.7~left(y+0.6~right)~right)~right)^{200}~right|-~frac{1}{2.3}~left(x-_-2~right)~right)", 2.5),
        F('F', "~left(x-2-_~right)~left(~left(~sin~left(~frac{95}{400}~pi~left(y+~frac{31}{100}~right)~right)~right)^{998}-~frac{1}{2}~left(x-2-_~right)~right)~left(~left(~sin~left(~frac{101}{400}~pi~left(y-~frac{49}{24}~right)~right)~right)^{998}-~left(x-2-_~right)~right)", 2.4),
        G('G', "~left(4~left(x-~frac{9}{2}-_~right)^{2}+9999~left(y-4~right)^{2}-2~right)~left(~left(3999~left(x-~frac{9}{2}-_~right)^{2}+~left(2~left(y-3~right)~right)^{2}~right)-4~right)~left(~left(~frac{11}{14}~left(y-4~right)~right)^{2}+2-x+_~right)", 3.6),
        H('H', "~left(x-~frac{201}{100}-_~right)~left(x-5-_~right)~left(4-y-~left(~frac{2}{3}~left(x-~frac{7}{2}-_~right)~right)^{25}~right)", 3.3),
        I('I', "~left(x-~frac{7}{2}-_~right)~left(-~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{1011}{500}-y~right)~left(~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{2989}{500}-y~right)", 3.2),
        J('J', "~left(999~left(y-5.999~right)-~left(x-4.9-_~right)~left(x-2.3-_~right)~right)~left(~left(x-4.25-_~right)-0.55~left(y-6.05~right)-~sqrt{2}~left|~sin~left(~frac{~sqrt{2}}{2}~left(~left(x-4.25-_~right)+0.55~left(y-6.05~right)~right)~right)~right|~right)", 3.3),
        K('K', "~left(x-~frac{201}{100}-_~right)~left(~left|y-4~right|-~left(x-~frac{201}{100}-_~right)~right)", 2.2),
        L('L', "~left(x-~frac{201}{100}-_~right)~left(-~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{1011}{500}-y~right)", 3.2),
        M('M', "~left(~left(x-5+~frac{y}{999}-_~right)~left(~left(~frac{5}{4}~left|x-~frac{7}{2}-_~right|~right)-~left(y-~frac{33}{8}~right)~right)~left(x-~frac{201}{100}+~frac{y}{999}-_~right)~right)", 3.2),
        N('N', "~left(~frac{8}{5}~left(x-_~right)-~frac{46}{5}+y~right)~left(x-_-2-~frac{1}{900}y~right)~left(x-_-~frac{9}{2}-~frac{1}{900}y~right)", 3),
        O('O', "~left(~frac{4~left(x-~frac{7}{2}-_~right)^{2}}{9}+~frac{~left(y-4~right)^{2}}{4}-1~right)", 3.4),
        P('P', "~left(x-~frac{201}{100}-_~right)~left(~frac{5}{2~sqrt{2~pi}}e^{~left(-~frac{25~left(y-~frac{49}{10}~right)^{2}}{8}~right)}-~frac{1}{2}~left(x-2-_~right)~right)", 2.2),
        Q('Q', "~left(~frac{4~left(x-~frac{7}{2}-_~right)^{2}}{9}+~frac{~left(y-4~right)^{2}}{4}-1~right)~left(~left(y~cos~left(0.7~right)-2.4~right)-~left(~sin~left(0.7~right)~left(x-3.3-_~right)~right)+1000~left(~left(y~sin~left(0.7~right)-2.4~right)+~left(~cos~left(0.7~right)~left(x-3.3-_~right)~right)~right)^{2}~right)", 3.2),
        R('R', "~left(x-~frac{201}{100}-_~right)~left(~left(~left(y-~frac{1}{5}~right)~cos~left(-~frac{1}{10}~right)-~left(x+~frac{2}{5}-_~right)~sin~left(-~frac{1}{10}~right)~right)-~left(~frac{7}{2}-~left(~left(~left(y-~frac{1}{5}~right)~sin~left(-~frac{1}{10}~right)+~left(x+~frac{2}{5}-_~right)~cos~left(-~frac{1}{10}~right)~right)-3~right)^{5}~right)~right)~left(~frac{5}{2~sqrt{2~pi}}e^{~left(-~frac{25~left(y-~frac{49}{10}~right)^{2}}{8}~right)}-~frac{1}{2}~left(x-2-_~right)~right)", 2.2),
        S('S', "~left(~sin~left(~frac{2}{3}~pi~left(y+~frac{17}{40}~right)~right)-~frac{2}{3}~left(x-~frac{43}{12}-_~right)~right)", 3.3),
        T('T', "~left(x-~frac{7}{2}-_~right)~left(~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{2989}{500}-y~right)", 3.2),
        U('U', "~left(~left(x-~frac{33}{10}-_~right)^{6}-y+2.01~right)", 2.7),
        V('V', "~left(~left|~frac{5}{2}~left(x-~frac{18}{5}-_~right)~right|-y+2~right)", 3.4),
        W('W', "~left(4~left|x-3-_~right|-~left(y-2~right)~right)~left(4~left|x-~frac{9}{2}-_~right|-~left(y-2~right)~right)", 3.7),
        X('X', "~left(~left|y-4~right|-~left|1.5~left(x-~frac{10}{3}-_~right)~right|~right)", 3),
        Y('Y', "~left(y-4.5-~left(x-3.3-_~right)^{2}~right)~left(y-2~left(x-1.5-_~right)~right)", 3),
        Z('Z', "~left(~frac{4}{3}~left(x-0.5-_~right)-y~right)~left(-~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{1011}{500}-y~right)~left(~frac{1}{100}~left(x-~frac{7}{2}-_~right)^{2}+~frac{2989}{500}-y~right)", 3.3),
        SPACE('-', "", 1.5),
        PERIOD('.', "~left(~left(x-~frac{5}{2}-_~right)^{2}+~left(y-~frac{5}{2}~right)^{2}-~frac{1}{4}~right)", 1.2),
        EXCLAMATION_MARK('!', "~left(~left(x-2.5-_~right)^{2}+~left(y-2.5~right)^{2}-0.2~right)~left(~frac{~left(x-2.5-_~right)^{2}}{0.8}+~frac{~left(y-4.53~right)^{2}}{7.5}-0.25~right)",1.2),
        LESS_THAN('<', "~left(x-2-_-1.3~left|y-4~right|~right)", 2.6),
        THREE('3', "~left(0.6~left(x-2-_~right)-~left(~sin~left(~frac{2~pi}{4}y~right)~right)^{~frac{2}{7}}~right)", 2),
        QUESTION_MARK('?', "~left(~frac{~left(~cos~left(1.3~right)0.7~left(x-_~right)-~sin~left(1.3~right)y+~sin~left(1.3~right)0.8~left(x-_~right)+~cos~left(1.3~right)y+0.4~right)^{2}}{5}+~frac{~left(~sin~left(1.3~right)0.8~left(x-_~right)+~cos~left(1.3~right)y-4.1+~left(~cos~left(1.3~right)0.7~left(x-_~right)-~sin~left(1.3~right)y+~sin~left(1.3~right)0.8~left(x-_~right)+~cos~left(1.3~right)y+0.4~right)^{2}~right)^{2}}{0.2}-0.2~right)~left(~left(x-3-_~right)^{2}+~left(y-~frac{5}{2}~right)^{2}-~frac{1}{8}~right)", 2),
        HEART('#', "~left(~left(~left(x-3.3-_~right)^{2}+~left(y-3.7~right)^{2}-1~right)^{3}-2~left(x-3.3-_~right)^{2}~left(y-3.7~right)^{3}~right)", 2.9),
        APOSTROPHE('\'', "~left(~left(x-2.5-_~right)^{2}+~left(y-2.8-~left(x-_~right)~right)^{2}-0.4^{2}~right)", 1.2), //MAKE SURE THIS IS ALSO FIXED
        MOON('}', "~left(~left(0.5~left(~cos~left(0.8~right)~left(x-4.9-_~right)+~sin~left(0.8~right)~left(y-2.9~right)~right)~right)^{2}+~left(~frac{~left(0.5~left(~sin~left(0.8~right)~left(x-4.9-_~right)-~cos~left(0.8~right)~left(y-2.9~right)~right)+~left(0.5~left(~cos~left(0.8~right)~left(x-4.9-_~right)+~sin~left(0.8~right)~left(y-2.9~right)~right)~right)^{2}~right)}{0.15}~right)^{2}-1~right)", 3.7);
        
        private char character;
        private String equation;
        private double distance;
        
        private Letter(char character, String equation, double distance){
            this.character = character;
            this.equation = equation;
            this.distance = distance;
        }
        
        public char getCharacter(){
            return this.character;
        }
        
        public String getEquation(){
            return this.equation;
        }
        
        public double getDistance(){
            return this.distance;
        }
    }
}