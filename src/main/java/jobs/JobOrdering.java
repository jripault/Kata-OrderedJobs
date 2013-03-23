package jobs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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

        List<Job> doneJobs = new ArrayList<Job>();
        List<Job> dependantJobs = new ArrayList<Job>();
        for (Job job : jobs) {
            if (job.dependsOn != null) {
                if(job.dependsOn == job){
                    throw new SelfReferencingException("A job cannot depend on itself");
                }
                if(searchForCircularDependencies(job, job.dependsOn)){
                    throw new CircularDependenciesException();
                }
                dependantJobs.add(job);
            } else {
                doneJobs.add(job);
                builder.append(job.name);
            }
        }

        while (dependantJobs.size() > 0) {
            List<Job> tempJobs = new ArrayList<Job>(dependantJobs);
            for (Job job : tempJobs) {
                if (doneJobs.contains(job.dependsOn)) {
                    builder.append(job.name);
                    doneJobs.add(job);
                    dependantJobs.remove(job);
                }
            }
        }
        return builder.toString();
    }

    private static boolean searchForCircularDependencies(Job rootJob, Job currentJob){
        if(currentJob == null){
            return false;
        }
        if(currentJob == rootJob){
            return true;
        }
        return searchForCircularDependencies(rootJob, currentJob.dependsOn);
    }
}
