import subprocess

subprocess.call(['java', '-jar', 'data-generator-1.0.jar',
	'--dataRequestType', 'PUBLICATION',
	'--parallelizationFactor', '4',
	'--totalNumberOfRecords', '100'])