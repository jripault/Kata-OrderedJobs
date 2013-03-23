import jobs.CircularDependenciesException;
import jobs.Job;
import jobs.JobOrdering;
import jobs.SelfReferencingException;
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
    public void shouldReturnOrderedJobsWhenMultipleJobsOneDependency(){
        Job a = new Job("a");
        Job c = new Job("c");
        Job b = new Job("b", c);
        String orderedJobs = JobOrdering.orderJobs(a, b, c);
        assertThat(orderedJobs).contains("a");
        assertThat(orderedJobs).contains("b");
        assertThat(orderedJobs).contains("c");
        assertThat(orderedJobs.indexOf("c")).isLessThan(orderedJobs.indexOf("b"));

    }

    @Test
    public void shouldReturnOrderedJobsWhenMultipleJobsMultipleDependencies(){
        Job a = new Job("a");
        Job f = new Job("f");
        Job c = new Job("c", f);
        Job b = new Job("b", c);
        Job d = new Job("d", a);
        Job e = new Job("e", b);
        String orderedJobs = JobOrdering.orderJobs(a, b, c, d, e, f);
        assertThat(orderedJobs).contains("a");
        assertThat(orderedJobs).contains("b");
        assertThat(orderedJobs).contains("c");
        assertThat(orderedJobs).contains("d");
        assertThat(orderedJobs).contains("e");
        assertThat(orderedJobs).contains("f");
        assertThat(orderedJobs.indexOf("c")).isLessThan(orderedJobs.indexOf("b"));
        assertThat(orderedJobs.indexOf("f")).isLessThan(orderedJobs.indexOf("c"));
        assertThat(orderedJobs.indexOf("b")).isLessThan(orderedJobs.indexOf("e"));
        assertThat(orderedJobs.indexOf("a")).isLessThan(orderedJobs.indexOf("d"));
    }

    @Test(expected = SelfReferencingException.class)
    public void shouldReturnExceptionWhenMultipleJobsSelfReferencingDependency(){
        Job a = new Job("a");
        Job b = new Job("b");
        Job c = new Job("c");
        c.dependsOn = c;
        JobOrdering.orderJobs(a, b, c);
    }

    @Test(expected = CircularDependenciesException.class)
    public void shouldReturnExceptionWhenCircularDependencies(){
        Job a = new Job("a");
        Job f = new Job("f");
        Job c = new Job("c", f);
        Job b = new Job("b", c);
        Job d = new Job("d", a);
        Job e = new Job("e");
        f.dependsOn = b;

        JobOrdering.orderJobs(a, b, c, d, e, f);
    }
}
