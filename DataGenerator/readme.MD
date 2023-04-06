## Homework 01:

#### Deployment and run configuration:
	The data generator is built on Java 11, and can be started from the command line after configuring the ./scripts/start_publication_generator.py
	Additionally, after the Java project is built, the jar artifact 'data-generator-1.0.jar' is produced.
	This jar artifact, together with the Python script and with the '/target/dependency-jars' can be placed in a folder to obtain a deployable package.
	The Python script facilitates the configuration of the input parameters. For example:
		'--dataRequestType', 'PUBLICATION'
		'--parallelizationFactor', '4'
		'--totalNumberOfRecords', '20000'
	Currently only the publications generator is implemented, while only the support functionality for a subcriptions generator is in place.
	For the subscription generator there will be implemented a GREEDY algorithm to split the fields population between the task generator threads based on the weight factor of each field.

#### Parallelization of the data set generation:
    It is implemented based on the 'java.util.concurrent' package, using threads (ExecutorService and Callable).
	The parallelization factor can be configurable easier from the input parameter '--parallelizationFactor'.

#### Execution examples:
	Machine details:
		OS: Windows10 x64
		Processor: Intel(R) Core(TM) i7-9750H CPU @ 2.60GHz   2.59 GHz
		Installed RAM: 16,0 GB (15,8 GB usable)
	
	1. Runtime configuration (no parallelization, generating 2000 records):
		subprocess.call(['java', '-jar', 'data-generator-1.0.jar',
			'--dataRequestType', 'PUBLICATION',
			'--parallelizationFactor', '1',
			'--totalNumberOfRecords', '2000'])
			
		Execution results:
			c:\Down\EBS>start_publication_generator.py
			apr. 06, 2023 6:29:07 P.M. com.fii.ebs.datagenerator.app.DataGeneratorApp main
			INFO: Starting the Data Generator.
			apr. 06, 2023 6:29:07 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-1 for numberOfRecords = 2000
			apr. 06, 2023 6:29:07 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-1 with execution time = PT0.2826502S
			apr. 06, 2023 6:29:07 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-1 completed successfully!
			apr. 06, 2023 6:29:07 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: PublicationDataGenerator completed with execution time = PT0.2876247S
	
	2. Runtime configuration (thread pool with 4 threads started, generating 2000 records):
		subprocess.call(['java', '-jar', 'data-generator-1.0.jar',
			'--dataRequestType', 'PUBLICATION',
			'--parallelizationFactor', '4',
			'--totalNumberOfRecords', '2000'])
			
		Execution results:
			c:\Down\EBS>start_publication_generator.py
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.app.DataGeneratorApp main
			INFO: Starting the Data Generator.
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-1 for numberOfRecords = 500
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-3 for numberOfRecords = 500
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-4 for numberOfRecords = 500
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-2 for numberOfRecords = 500
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-4 with execution time = PT0.3017826S
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-3 with execution time = PT0.3067688S
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-2 with execution time = PT0.3077684S
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-1 with execution time = PT0.3077684S
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-1 completed successfully!
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-2 completed successfully!
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-3 completed successfully!
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-4 completed successfully!
			apr. 06, 2023 6:28:33 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: PublicationDataGenerator completed with execution time = PT0.3177417S
		
    3. Runtime configuration (no parallelization, generating 20000 records):
		subprocess.call(['java', '-jar', 'data-generator-1.0.jar',
			'--dataRequestType', 'PUBLICATION',
			'--parallelizationFactor', '1',
			'--totalNumberOfRecords', '20000'])
			
		Execution results:
			c:\Down\EBS>start_publication_generator.py
			apr. 06, 2023 6:33:32 P.M. com.fii.ebs.datagenerator.app.DataGeneratorApp main
			INFO: Starting the Data Generator.
			apr. 06, 2023 6:33:32 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-1 for numberOfRecords = 20000
			apr. 06, 2023 6:33:32 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-1 with execution time = PT0.5268945S
			apr. 06, 2023 6:33:32 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-1 completed successfully!
			apr. 06, 2023 6:33:32 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: PublicationDataGenerator completed with execution time = PT0.532848S
	
	4. Runtime configuration (thread pool with 4 threads started, generating 20000 records):
		subprocess.call(['java', '-jar', 'data-generator-1.0.jar',
			'--dataRequestType', 'PUBLICATION',
			'--parallelizationFactor', '4',
			'--totalNumberOfRecords', '20000'])
		
		Execution results:
			c:\Down\EBS>start_publication_generator.py
			apr. 06, 2023 6:34:26 P.M. com.fii.ebs.datagenerator.app.DataGeneratorApp main
			INFO: Starting the Data Generator.
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-4 for numberOfRecords = 5000
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-2 for numberOfRecords = 5000
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-3 for numberOfRecords = 5000
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask handled in thread = pool-1-thread-1 for numberOfRecords = 5000
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-4 with execution time = PT0.4962852S
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-3 with execution time = PT0.5001653S
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-1 with execution time = PT0.504145S
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator$PublicationDataGeneratorTask call
			INFO: PublicationDataGeneratorTask completed in thread = pool-1-thread-2 with execution time = PT0.5025661S
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-1 completed successfully!
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-2 completed successfully!
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-3 completed successfully!
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: Task = pool-1-thread-4 completed successfully!
			apr. 06, 2023 6:34:27 P.M. com.fii.ebs.datagenerator.generators.PublicationDataGenerator process
			INFO: PublicationDataGenerator completed with execution time = PT0.5131527S
	
	These execution results show that the parallelization execution is obtaining better times only when the amount of records to be generated is bigger than 20000 records.