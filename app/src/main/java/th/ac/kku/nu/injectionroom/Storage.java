package th.ac.kku.nu.injectionroom;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by nopph on 10/22/2017.
 */

public class Storage {
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
        }
    }
}
