package answers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.Map;

/**
 * Формат ответа с передачей объекта
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class DataRestAnswer extends RestAnswer
{
	private Map<String, Object> data;

	public DataRestAnswer(Map<String, Object> data)
	{
		this.data = data;
	}

	public Map<String, Object> getData()
	{
		return data;
	}

	public void setData(Map<String, Object> data)
	{
		this.data = data;
	}
}
