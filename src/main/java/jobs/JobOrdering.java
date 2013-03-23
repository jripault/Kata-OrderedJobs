package jobs;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class JobOrdering {
    public static String orderJobs(Job... jobs) {
        if (jobs.length == 0)
            return "";
        StringBuilder builder = new StringBuilder();
        for (Job job : jobs) {
            builder.append(job.name);
        }
        return builder.toString();
    }
}
