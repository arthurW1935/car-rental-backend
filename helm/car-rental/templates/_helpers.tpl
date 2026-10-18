{{- define "car-rental.name" -}}{{ .Chart.Name }}{{- end -}}

{{- define "car-rental.labels" -}}
app.kubernetes.io/part-of: car-rental
app.kubernetes.io/managed-by: {{ .Release.Service }}
helm.sh/chart: {{ .Chart.Name }}-{{ .Chart.Version }}
{{- end -}}

{{- define "car-rental.app.selector" -}}
app.kubernetes.io/name: car-rental-backend
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "car-rental.mysql.selector" -}}
app.kubernetes.io/name: mysql
app.kubernetes.io/instance: {{ .Release.Name }}
{{- end -}}

{{- define "car-rental.mysql.host" -}}{{ .Release.Name }}-mysql{{- end -}}
