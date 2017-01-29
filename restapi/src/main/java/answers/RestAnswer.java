package answers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.sql.Timestamp;
import java.util.List;
import java.util.Map;

/**
 * Created in project RestfuL_Project on 29.01.17
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public abstract class RestAnswer
{
	private Timestamp timestamp;

	private int status = 200;

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>
	//  Contructors
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>

	{
		updateTimestamp();
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>
	//  Getters
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>

	public Timestamp getTimestamp()
	{
		return timestamp;
	}

	public int getStatus()
	{
		return status;
	}

	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>
	//  Setters
	//~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~>

	public void updateTimestamp() {
		timestamp = new Timestamp(System.currentTimeMillis());
	}

	public void setStatus(int status)
	{
		this.status = status;
	}
}
