package jobs;

import java.util.ArrayList;
import java.util.List;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class JobOrdering {
    public static String orderJobs(Job... jobs) {
        if (jobs.length == 0)
            return "";
        StringBuilder builder = new StringBuilder();

        List<Job> dependantJobs = new ArrayList<Job>();
        for (Job job : jobs) {
            if(job.dependsOn != null){
                dependantJobs.add(job);
            }else{
                builder.append(job.name);
            }
        }

        for (Job job : dependantJobs) {
            builder.append(job.name);
        }
        return builder.toString();
    }
}
