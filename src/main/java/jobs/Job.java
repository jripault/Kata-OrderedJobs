package jobs;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
public class Job {
    public String name = "";
    public Job dependsOn = null;

    public Job() {
    }

    public Job(String name) {
        this.name = name;
    }

    public Job(String name, Job dependsOn) {
        this.name = name;
        this.dependsOn = dependsOn;
    }
}
