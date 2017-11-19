package th.ac.kku.nu.injectionroom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is contain a lot of static content which provide data for this applications!
 * Created by nopph on 10/22/2017.
 */

public class Storage {

    public static final String SYRINGE_TYPE_KEY = "syrType" , THREE_CC_STR = "3cc" , ONE_CC_STR = "1cc" , INSULIN_SYRINGE_STR = "insulin";
    public static final String BUNDLE_EQP = "Bundle_eqp";
    public static final float ANGLE15_RATIO = (float) (2 - Math.sqrt(3));
    public enum SYRINGE_TYPE{
        THREE_CC , ONE_CC , INSULIN_SYRINGE
    }

    public enum NEEDLE_NO{
        NO_27 , NO_26 , NO_25 , NO_24 , NO_23 , NO_22 , NO_21
    }

    public enum DRUG_TYPE{
        PVRV , DMPA , INSULIN
    }

    public static String currentTaskTypeKey = "";
    public static Integer currentTaskNumber = 0;

    public static final Double MAX_VOL_3ML = 3.0, MAX_VOL_1ML = 1.0 , MAX_VOL_INSULIN = 1.0;

    static public class Task{
        public static ArrayList<String> taskTypeKeyList = new ArrayList<>();
        public static HashMap<String , Integer> taskTypeResource = new HashMap<>();
        public static HashMap<String , ArrayList<Integer>> taskListResource = new HashMap<>();

        public static void implementTask(){
            taskTypeKeyList.add(0,"intradermal");
            taskTypeKeyList.add(1,"intramuscular");
            taskTypeKeyList.add(2,"subcutaneous");

            taskTypeResource.put(taskTypeKeyList.get(0),R.string.intradermal);
            taskTypeResource.put(taskTypeKeyList.get(1),R.string.intramuscular);
            taskTypeResource.put(taskTypeKeyList.get(2),R.string.subcutaneous);

            //Add intradermal task
            ArrayList<Integer> temp = new ArrayList<>();
            temp.add(R.string.task_intradermal_0);
            taskListResource.put(taskTypeKeyList.get(0),temp);

            //Add intramuscular task
            temp = new ArrayList<>();
            temp.add(R.string.task_intramus_0);
            taskListResource.put(taskTypeKeyList.get(1),temp);

            //Add subcutaneous task
            temp = new ArrayList<>();
            temp.add(R.string.task_sub_0);
            taskListResource.put(taskTypeKeyList.get(2),temp);

            //Init score set
            Score.initScoreSet();
        }
    }

    static public class Score {
        public static Integer[] scoreSet;
        public static void initScoreSet(){
            scoreSet = new Integer[Task.taskTypeKeyList.size()];
        }
    }

    static public class InjectionProcess{
        public static float MAXDEPT00 = (float)3.0 , MINDEPT00 = (float)2.0 ,
                MAXDEPT20 = (float)17.00 , MINDEPT20 = (float)15.00 ,
                MAXDEPT10 = (float)20.00 , MINDEPT10 = (float)18.00;
        public static float DRUG_VOLUMN00_MIN = (float) 0.09 , DRUG_VOLUMN00_MAX = (float) 0.11 ,
                DRUG_VOLUMN10_MIN = (float) 0.99 , DRUG_VOLUMN10_MAX = (float) 1.01 ,
                DRUG_VOLUMN20_MIN = (float) 0.09 , DRUG_VOLUMN20_MAX = (float) 0.11;
        public static int totalPoint00 =0 , totalPoint10=0 , totalPoint20=0;

        public static boolean glove = false , cottonAlc = false , injectCorrect = false , deptCorrect = false , useCotton = false;

        public static boolean syringe = false , needleSize = false , drug=false , drugVolumeCorrect;
        public static float drugVolumn = 0;

        public static float dept = 0;
        public static void resetProcessMarks(){
            glove = false;
            cottonAlc = false;
            injectCorrect = false;
            deptCorrect = false;
            useCotton = false;
            syringe = false;
            needleSize = false;
            drug = false;
            drugVolumn = 0;
            dept = 0;
            totalPoint00 = 0;
            totalPoint10 = 0;
            totalPoint20 = 0;
            currentTaskNumber = 0;
            currentTaskTypeKey = "";
        }
    }
}
