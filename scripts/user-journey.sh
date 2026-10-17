#!/usr/bin/env bash
# End-to-end user journey against a running deployment: the "does it work for a user?" check.
# Usage: scripts/user-journey.sh [base-url]   (default http://localhost:8080)
set -euo pipefail
BASE="${1:-http://localhost:8080}"
RUN=$(date +%s)
json() { curl -sf -H 'content-type: application/json' "$@"; }
# Book 30-34 days ahead so it always counts as a future reservation (portable across macOS/Linux)
START=$(python3 -c 'import datetime as d; print(d.date.today() + d.timedelta(days=30))')
END=$(python3 -c 'import datetime as d; print(d.date.today() + d.timedelta(days=34))')

echo "Version:  $(curl -sf "$BASE/actuator/info" | jq -r .app.version)"

CUSTOMER=$(json -XPOST "$BASE/customers" -d "{\"name\":\"Journey $RUN\",\"phone\":\"9000000000\",\"mail\":\"journey$RUN@example.com\",\"address\":\"Pune\"}" | jq -r .id)
echo "1. Customer registered      id=$CUSTOMER"

VEHICLE=$(json -XPOST "$BASE/vehicle" -d "{\"manufacturer\":\"Maruti\",\"model\":\"Swift\",\"year\":2023,
  \"licensePlateNumber\":\"MH12-$RUN\",\"currentMileage\":100.0,\"vehicleType\":\"FOUR_WHEELER\",\"vehicleStatus\":\"AVAILABLE\",
  \"branch\":{\"location\":\"Pune\",\"phoneNumber\":\"8000000000\",\"email\":\"pune$RUN@example.com\",
  \"manager\":{\"name\":\"Manager $RUN\",\"phoneNumber\":\"7000000000\",\"email\":\"mgr$RUN@example.com\"}}}" | jq -r .id)
echo "2. Vehicle added to branch  id=$VEHICLE"

COST=$(curl -sf "$BASE/rental/cost?vehicleId=$VEHICLE&startDate=$START&endDate=$END")
echo "3. Price quoted             cost=$COST"

RENTAL=$(json -XPOST "$BASE/rental" -d "{\"vehicleId\":$VEHICLE,\"customerId\":$CUSTOMER,\"startDate\":\"$START\",\"endDate\":\"$END\"}" | jq -r .id)
echo "4. Rental booked            id=$RENTAL"

curl -sf "$BASE/rental/reservation/customer/$CUSTOMER" | jq -e "map(.id) | index($RENTAL) != null" >/dev/null
echo "5. Booking visible in customer's reservations"
echo "JOURNEY OK"
