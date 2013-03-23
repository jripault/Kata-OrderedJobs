package jobs;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class JobOrdering {
    public static String orderJobs(Job job) {
        if ("".equals(job.name))
            return "";

        return job.name;
    }
}
