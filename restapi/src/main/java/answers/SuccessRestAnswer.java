package answers;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * Формат передачи ответа с массивом
 *
 * Created in project RestfuL_Project on 29.01.17
 */
@JsonIgnoreProperties(ignoreUnknown = false)
public class SuccessRestAnswer extends RestAnswer
{
	private Boolean success;

	public SuccessRestAnswer(Boolean success)
	{
		this.success = success;
	}

	public Boolean getSuccess()
	{
		return success;
	}

	public void setSuccess(Boolean success)
	{
		this.success = success;
	}
}
