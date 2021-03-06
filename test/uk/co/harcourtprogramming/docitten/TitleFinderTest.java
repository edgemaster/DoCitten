package uk.co.harcourtprogramming.docitten;

import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import org.junit.Test;
import uk.co.harcourtprogramming.internetrelaycats.RelayCat;
import uk.co.harcourtprogramming.internetrelaycats.Service;

public class TitleFinderTest
{
	// Pefroming a depth-first search of my bookmarks, this is the first thing
	// As it isn't going to change title (unlike, possibly, Google), it seems
	// like a good bet.
	private final String uri = "http://www.artima.com/weblogs/viewpost.jsp?thread=142428";
	private final String title = "Java API Design Guidelines";
	private final String site = "www.artima.com";
	private final String expected = String.format("[%s] %s", site, title);

	private String target = null;
	private String message = null;

	private final RelayCat cat = new RelayCat() {
		@Override
		public void message(String target, String message)
		{
			TitleFinderTest.this.target  = target;
			TitleFinderTest.this.message = message;
		}

		@Override
		public void act(String target, String message)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void setTopic(String target, String topic)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void join(String channel)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public void leave(String channel)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String getNick()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String[] names(String channel)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public String[] channels()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public boolean isConnected()
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}

		@Override
		public <Clazz extends Service> List<Clazz> getServicesByClass(Class<Clazz> clazz)
		{
			throw new UnsupportedOperationException("Not supported yet.");
		}
	};

	@Test
	@SuppressWarnings("CallToThreadRun")
	public void TestTitleResolution() throws InterruptedException
	{
		final String nick = "bob";

		LinkResolver r = new LinkResolver(uri, cat, nick);

		r.run();

		assertNotNull(target);
		assertEquals(nick, target);
		assertNotNull(message);
		assertEquals(expected, message);
	}
}
