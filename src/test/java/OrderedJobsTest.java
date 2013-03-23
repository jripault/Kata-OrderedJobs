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
        assertThat(orderedJobs).isEqualTo("abc");
    }
    @Test
    public void shouldReturnOrderedJobsWhenJobsHasNoDependencies2(){
        String orderedJobs = JobOrdering.orderJobs(new Job("a"), new Job("c"), new Job("b"));
        assertThat(orderedJobs).isEqualTo("acb");
    }
}
