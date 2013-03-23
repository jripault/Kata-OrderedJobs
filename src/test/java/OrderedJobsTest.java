import jobs.Job;
import jobs.JobOrdering;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import static org.fest.assertions.api.Assertions.*;

/**
 * User: JRI <julien.ripault@atos.net>
 * Date: 23/03/13
 */
@RunWith(JUnit4.class)
public class OrderedJobsTest {
    @Test
    public void shouldEmptyJobsReturnNothing(){
        String orderedJobs = JobOrdering.orderJobs();
        assertThat(orderedJobs).isEqualTo("");
    }

    @Test
    public void shouldReturnJobWhenJobHasNoDependencies(){
        String orderedJobs = JobOrdering.orderJobs(new Job("a"));
        assertThat(orderedJobs).isEqualTo("a");
    }

    @Test
    public void shouldReturnOrderedJobsWhenJobsHasNoDependencies(){
        String orderedJobs = JobOrdering.orderJobs(new Job("a"), new Job("b"), new Job("c"));
        assertThat(orderedJobs).contains("a");
        assertThat(orderedJobs).contains("b");
        assertThat(orderedJobs).contains("c");

        orderedJobs = JobOrdering.orderJobs(new Job("a"), new Job("c"), new Job("b"));
        assertThat(orderedJobs).contains("a");
        assertThat(orderedJobs).contains("b");
        assertThat(orderedJobs).contains("c");
    }

    @Test
    public void shouldReturnOrderedJobsWhenMultipleJobsOneDependencies(){
        Job a = new Job("a");
        Job c = new Job("c");
        Job b = new Job("b", c);
        String orderedJobs = JobOrdering.orderJobs(a, b, c);
        assertThat(orderedJobs).contains("a");
        assertThat(orderedJobs).contains("b");
        assertThat(orderedJobs).contains("c");
        assertThat(orderedJobs.indexOf("c")).isLessThan(orderedJobs.indexOf("b"));

    }
}
