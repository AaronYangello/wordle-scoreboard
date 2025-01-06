import subprocess
import os

def start_postgres_server(port=5432, server='localhost', username='postgres', password='0000'):
    """
    Starts a PostgreSQL server connection using psql.

    Args:
        db_name (str): The name of the database to connect to.
        port (int): The port number to use for the connection.

    Raises:
        ValueError: If the port number is not a positive integer.
        FileNotFoundError: If the psql executable is not found.
        RuntimeError: If the psql command fails to execute.
    """
    # Validate input parameters
    if not isinstance(port, int) or port <= 0:
        raise ValueError("Port number must be a positive integer")

    # Determine client encoding
    client_encoding = 'UTF8'
    if os.name == 'nt':  # Windows
        try:
            result = subprocess.run(['chcp'], capture_output=True, text=True)
            if '932' in result.stdout:
                client_encoding = 'SJIS'
        except FileNotFoundError:
            print("Warning: chcp command not found. Using default client encoding.")

    # Set psql path
    psql_path = r'K:\Program Files\PostgreSQL\17\bin\psql.exe'

    # Check if psql executable exists
    if not os.path.exists(psql_path):
        raise FileNotFoundError(f"psql executable not found at {psql_path}")

    # Run psql with password
    try:
        psql_command = f'{psql_path} -h {server} -U {username}'
        # Use PGPASSWORD environment variable to set password
        os.environ['PGPASSWORD'] = password
        subprocess.run(psql_command, check=True)
        # Clear PGPASSWORD environment variable for security
        del os.environ['PGPASSWORD']
    except subprocess.CalledProcessError as e:
        raise RuntimeError(f"psql command failed with exit code {e.returncode}")

def main():
    # Start PostgreSQL servers
    try:
        start_postgres_server()
    except Exception as e:
        print(f"Error: {e}")

if __name__ == "__main__":
    main()