package answers;

/**
 * Формат передачи ответа с ошибкой
 *
 * Created in project RestfuL_Project on 29.01.17
 */
public class ErrorRestAnswer extends RestAnswer
{
	private String error;

	public ErrorRestAnswer(int status, String error)
	{
		setStatus(status);
		this.error = error;
	}

	public String getError()
	{
		return error;
	}

	public void setError(String error)
	{
		this.error = error;
	}
}
