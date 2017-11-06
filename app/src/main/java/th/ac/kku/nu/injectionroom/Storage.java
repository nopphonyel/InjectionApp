package th.ac.kku.nu.injectionroom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is contain a lot of static content which provide data for this applications!
 * Created by nopph on 10/22/2017.
 */

public class Storage {

    public static final String SYRINGE_TYPE_KEY = "syrType" , THREE_CC_STR = "3cc" , INSULIN_SYRINGE_STR = "insulin";
    public static final String BUNDLE_EQP = "Bundle_eqp";
    public static final float ANGLE15_RATIO = (float) (2 - Math.sqrt(3));
    public enum SYRINGE_TYPE{
        THREE_CC , INSULIN_SYRINGE
    }

    public enum NEEDLE_NO{
        NO_27 , NO_24 , NO_21
    }

    public enum DRUG_TYPE{
        PVRV , DMPA , INSULIN
    }

    public static String currentTaskTypeKey = "";
    public static Integer currentTaskNumber = 0;

    public static final Double MAX_VOL_3CCML = 3.0, MAX_VOL_INSULIN_ML = 1.0;

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
                MAXDEPT20 = (float)25.4 , MINDEPT20 = (float)19.05 ,
                MAXDEPT10 = (float)25.4 , MINDEPT10 = (float)19.05;
        public static float DRUG_VOLUMN00 = (float) 0.1 , DRUG_VOLUMN10 = (float) 0.15 , DRUG_VOLUMN20 = (float) 0.1;
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
        }
    }
}
