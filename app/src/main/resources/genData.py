import json
import random
from faker import Faker
fake = Faker()
# Let's generate a new set of 25 people with the complete set of information as requested earlier
new_test_data = []

for _ in range(25):
    person = {
        "id": fake.uuid4(),
        "first_name": fake.first_name(),
        "last_name": fake.last_name(),
        "ssn": fake.ssn(),
        "age": random.randint(18, 100),

        "gender": random.choice(["Male", "Female", "Other"]),
        "race": random.choice(["Asian", "Black or African American", "White", "American Indian or Alaska Native", "Native Hawaiian or Other Pacific Islander"]),
        "ethnicity": random.choice(["Hispanic or Latino", "Not Hispanic or Latino"]),
        "phone_number": fake.phone_number(),
        "address": {
            "street_address": fake.street_address(),
            "city": fake.city(),
            "state": fake.state(),
            "zip_code": fake.zipcode()
        }
    }
    new_test_data.append(person)
# Convert the new list to JSON format
new_json_data = json.dumps(new_test_data, indent=4)
# Write the new JSON data to a new file
with open("people.json", "w") as f:
    f.write(new_json_data)
